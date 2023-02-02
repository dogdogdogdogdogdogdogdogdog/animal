package com.lovepet.animal.service;

import com.lovepet.animal.dto.MissingAnimalRequest;

import com.lovepet.animal.model.MissingData;

import java.util.List;


public interface MissingService {
    public void createMissing(MissingAnimalRequest missingAnimalRequest);

    public List<MissingData> getMissingById(Integer id);
}
