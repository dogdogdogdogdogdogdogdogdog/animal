package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.MissingDao;
import com.lovepet.animal.dto.MissingAnimalRequest;
import com.lovepet.animal.dto.MissingAnimalsQueryParams;
import com.lovepet.animal.model.MissingData;
import com.lovepet.animal.service.MissingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;


@Component
public class MissingServiceImpl implements MissingService {
    @Autowired
    private MissingDao missingDao;

    @Override
    public void createMissing(MissingAnimalRequest missingAnimalRequest) {

        missingDao.createMissing(missingAnimalRequest);
    }

    @Override
    public List<MissingData> getMissingAnimals(MissingAnimalsQueryParams missingAnimalsQueryParams) {
        return missingDao.getMissingAnimals(missingAnimalsQueryParams);
    }

    @Override
    public List<MissingData> getMissingById(Integer id) {
        return missingDao.getMissingById(id);
    }

    @Override
    public Integer countMissingAnimals(MissingAnimalsQueryParams missingAnimalsQueryParams) {
        return missingDao.countMissingAnimals(missingAnimalsQueryParams);
    }
}
