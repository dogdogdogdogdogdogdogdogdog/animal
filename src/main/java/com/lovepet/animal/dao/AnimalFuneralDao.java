package com.lovepet.animal.dao;

import com.lovepet.animal.dto.AnimalFuneralQueryParams;
import com.lovepet.animal.dto.AnimalFuneralRequest;
import com.lovepet.animal.model.AnimalFuneral;

import java.util.List;

public interface AnimalFuneralDao {

    Integer countFuneral(AnimalFuneralQueryParams animalFuneralQueryParams);

    List<AnimalFuneral> getFunerals(AnimalFuneralQueryParams animalFuneralQueryParams);

    AnimalFuneral getFuneralById(Integer funeralId);

    Integer createFuneral(AnimalFuneralRequest animalFuneralRequest);

    void updateFuneral(Integer funeralId, AnimalFuneralRequest animalFuneralRequest);

    void deleteFuneralById(Integer funeralId);
}
