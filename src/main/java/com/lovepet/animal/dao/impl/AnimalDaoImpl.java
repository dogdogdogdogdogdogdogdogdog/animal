package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.AnimalDao;
import com.lovepet.animal.dto.AnimalQueryParams;

import com.lovepet.animal.rowmapper.AnimalRowmapper;
import com.lovepet.animal.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AnimalDaoImpl implements AnimalDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Page getAnimals(AnimalQueryParams animalQueryParams) {
        Page page = new Page();
        Map<String, Object> map = new HashMap<>();
        String sql = "SELECT animal_id, animal_kind, animal_Variety, animal_sex, animal_colour, animal_bodytype, animal_age, animal_sterilization, " +
                "animal_bacterin, animal_foundplace, animal_remark, album_file, shelter_name, shelter_address, shelter_tel, animal_opendate, " +
                "animal_createtime, animal_update, animal_status animal_area FROM public_animal WHERE 1=1 ";



            if (animalQueryParams.getShelter()!=null) {
                sql += " and shelter_name= :shelterName ";
                map.put("shelterName", animalQueryParams.getShelter());
            }
            if (animalQueryParams.getKind()!=null) {
                sql +=  " and animal_kind= :animalKind ";
                map.put("animalKind", animalQueryParams.getKind());
            }
            if(animalQueryParams.getId()!=null){
                sql +=  " and animal_id= :animalId ";
                map.put("animalId", animalQueryParams.getId());
            }
            page.setTotal(getCount(animalQueryParams, map));




        String splitPage = " limit :limit offset :offset ";
        sql = sql + splitPage;
        map.put("limit", animalQueryParams.getLimit());
        map.put("offset", animalQueryParams.getOffset());

        page.setResults(namedParameterJdbcTemplate.query(sql, map, new AnimalRowmapper()));
        page.setLimit(animalQueryParams.getLimit());
        page.setOffset(animalQueryParams.getOffset());
        return page;
    }




    private Integer getCount(AnimalQueryParams animalQueryParams, Map map) {
        //String sql="select count(*) from shelter INNER join (select time.animal_id,animal_kind,animal_Variety,animal_sex,animal_bodytype,animal_colour,animal_age,animal_sterilization,animal_bacterin,animal_foundplace,animal_remark,album_file,animal_shelter_pkid,animal_opendate,animal_update,animal_createtime from animal INNER join time   on animal.animal_id= time.animal_id) animalJoinTime on shelter.animal_shelter_pkid=animalJoinTime.animal_shelter_pkid where 1=1 ";
        String sql = "SELECT COUNT(*) FROM public_animal WHERE 1=1";


        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return    total;
    }







}
