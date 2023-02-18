package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.AnimalFuneralDao;
import com.lovepet.animal.dto.AnimalFuneralQueryParams;
import com.lovepet.animal.dto.AnimalFuneralRequest;
import com.lovepet.animal.model.AnimalFuneral;
import com.lovepet.animal.service.AnimalFuneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimalFuneralServiceImpl implements AnimalFuneralService {

    @Autowired
    private AnimalFuneralDao animalFuneralDao;

    @Override
    public Integer countFuneral(AnimalFuneralQueryParams animalFuneralQueryParams) {
        return animalFuneralDao.countFuneral(animalFuneralQueryParams);
    }

    @Override
    public List<AnimalFuneral> getFunerals(AnimalFuneralQueryParams animalFuneralQueryParams) {
        return animalFuneralDao.getFunerals(animalFuneralQueryParams);
    }

    @Override
    public AnimalFuneral getFuneralById(Integer funeralId) {
        return animalFuneralDao.getFuneralById(funeralId);
    }

    @Override
    public Integer createFuneral(AnimalFuneralRequest animalFuneralRequest) {
        return animalFuneralDao.createFuneral(animalFuneralRequest);
    }

    @Override
    public void updateFuneral(Integer funeralId, AnimalFuneralRequest animalFuneralRequest) {
        animalFuneralDao.updateFuneral(funeralId, animalFuneralRequest);
    }

    @Override
    public void deleteFuneralById(Integer funeralId) {
        animalFuneralDao.deleteFuneralById(funeralId);
    }
}
