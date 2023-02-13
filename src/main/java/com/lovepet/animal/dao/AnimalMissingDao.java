package com.lovepet.animal.dao;

import com.lovepet.animal.dto.AnimalMissingQueryParams;
import com.lovepet.animal.dto.AnimalMissingRequest;
import com.lovepet.animal.model.AnimalMissing;

import java.util.List;

public interface AnimalMissingDao {

    List<AnimalMissing> getAnimalsMissingComboBox();

    Integer countAnimalMissing(AnimalMissingQueryParams animalMissingQueryParams);

    List<AnimalMissing> getAnimalsMissing(AnimalMissingQueryParams animalMissingQueryParams);

    AnimalMissing getAnimalMissingById(Integer animalMissingId);

    Integer createAnimalMissing(AnimalMissingRequest animalMissingRequest);

    void  updateAnimalMissing(Integer animalMissingId, AnimalMissingRequest animalMissingRequest);

    void deleteAnimalMissingById(Integer animalMissingUserId, Integer animalMissingId);
}
