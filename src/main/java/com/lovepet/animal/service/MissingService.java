package com.lovepet.animal.service;

import com.lovepet.animal.dto.MissingAnimalRequest;

import com.lovepet.animal.dto.MissingAnimalsQueryParams;
import com.lovepet.animal.model.MissingData;

import java.util.List;


public interface MissingService {
    void createMissing(MissingAnimalRequest missingAnimalRequest);
     List<MissingData> getMissingAnimals(MissingAnimalsQueryParams missingAnimalsQueryParams);
     List<MissingData> getMissingById(Integer id);
     Integer countMissingAnimals(MissingAnimalsQueryParams missingAnimalsQueryParams);
}
