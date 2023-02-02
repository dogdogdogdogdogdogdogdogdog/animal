package com.lovepet.animal.dao;

import com.lovepet.animal.dto.MissingAnimalRequest;

import com.lovepet.animal.model.MissingData;
import com.lovepet.animal.model.PublishData;

import java.util.List;

public interface MissingDao {
    public void createMissing(MissingAnimalRequest missingAnimalRequest);

    public List<MissingData> getMissingById(Integer id);
}
