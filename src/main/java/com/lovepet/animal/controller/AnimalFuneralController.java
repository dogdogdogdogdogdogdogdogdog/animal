package com.lovepet.animal.controller;

import com.lovepet.animal.dto.AnimalFuneralQueryParams;
import com.lovepet.animal.dto.AnimalFuneralRequest;
import com.lovepet.animal.model.AnimalFuneral;
import com.lovepet.animal.service.AnimalFuneralService;
import com.lovepet.animal.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class AnimalFuneralController {

    @Autowired
    private AnimalFuneralService animalFuneralService;

    @GetMapping("/funerals")
    public ResponseEntity<Page<AnimalFuneral>> getAnimalFunerals(
            // 查詢條件 Filtering
            @RequestParam(required = false) String area,
            // 分頁 Pagination
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        AnimalFuneralQueryParams animalFuneralQueryParams = new AnimalFuneralQueryParams();
        animalFuneralQueryParams.setArea(area);
        animalFuneralQueryParams.setLimit(limit);
        animalFuneralQueryParams.setOffset(offset);

        // 取得 product list
        List<AnimalFuneral> animalFuneralList = animalFuneralService.getFunerals(animalFuneralQueryParams);

        // 取得 product 總數
        Integer total = animalFuneralService.countFuneral(animalFuneralQueryParams);

        // 分頁
        Page<AnimalFuneral> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(animalFuneralList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/funerals/{funeralId}")
    public ResponseEntity<AnimalFuneral> getAnimalFuneral(@PathVariable Integer funeralId) {
        AnimalFuneral animalFuneral = animalFuneralService.getFuneralById(funeralId);

        if (animalFuneral != null) {
            return ResponseEntity.status(HttpStatus.OK).body(animalFuneral);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/funerals")
    public ResponseEntity<AnimalFuneral> createAnimalFuneral(@RequestBody @Valid AnimalFuneralRequest animalFuneralRequest) {
        Integer animalFuneralId = animalFuneralService.createFuneral(animalFuneralRequest);

        AnimalFuneral animalFuneral = animalFuneralService.getFuneralById(animalFuneralId);

        return ResponseEntity.status(HttpStatus.CREATED).body(animalFuneral);
    }

    @PutMapping("/funerals/{funeralId}")
    public ResponseEntity<AnimalFuneral> updateAnimalFuneral(@PathVariable Integer funeralId,
                                                             @RequestBody @Valid AnimalFuneralRequest animalFuneralRequest) {
        AnimalFuneral animalFuneral = animalFuneralService.getFuneralById(funeralId);

        if (animalFuneral == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        animalFuneralService.updateFuneral(funeralId, animalFuneralRequest);

        AnimalFuneral updatedAnimalFuneral = animalFuneralService.getFuneralById(funeralId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedAnimalFuneral);
    }

    @DeleteMapping("/funerals/{funeralId}")
    public ResponseEntity<AnimalFuneral> deleteAnimalFuneral(@PathVariable Integer funeralId) {
        animalFuneralService.deleteFuneralById(funeralId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
