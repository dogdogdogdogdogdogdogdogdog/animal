package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.AnimalDao;
import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.rowmapper.AnimalRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimalDaoImpl implements AnimalDao {
    @Autowired
   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
   private Map<String,Object> map=new HashMap<>();
    @Override
    public List<AnimalData> getAnimals(AnimalQueryParams animalQueryParams) {
        String sql="select animal_id,animal_kind,animal_Variety,animal_sex,animal_bodytype,animal_colour,animal_age,animal_sterilization,animal_bacterin,animal_foundplace,animal_remark,album_file,shelter_name,shelter_address,shelter_tel,animal_opendate,animal_createtime,animal_update from shelter INNER join (select time.animal_id,animal_kind,animal_Variety,animal_sex,animal_bodytype,animal_colour,animal_age,animal_sterilization,animal_bacterin,animal_foundplace,animal_remark,album_file,animal_shelter_pkid,animal_opendate,animal_update,animal_createtime from animal INNER join time   on animal.animal_id= time.animal_id) animalJoinTime on shelter.animal_shelter_pkid=animalJoinTime.animal_shelter_pkid ";
      String splitPage=" limit :limit offset :offset ";
        sql=sql+splitPage;
      map.put("limit",animalQueryParams.getLimit());
        map.put("offset",animalQueryParams.getOffset());

      List<AnimalData> dataList=  namedParameterJdbcTemplate.query(sql,map,new AnimalRowmapper());
      return dataList;
    }

    @Override
    public List<Integer> getPages(AnimalQueryParams animalQueryParams) {
        String sql="select count(*) from shelter INNER join (select time.animal_id,animal_kind,animal_Variety,animal_sex,animal_bodytype,animal_colour,animal_age,animal_sterilization,animal_bacterin,animal_foundplace,animal_remark,album_file,animal_shelter_pkid,animal_opendate,animal_update,animal_createtime from animal INNER join time   on animal.animal_id= time.animal_id) animalJoinTime on shelter.animal_shelter_pkid=animalJoinTime.animal_shelter_pkid ";

        Integer pages=namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);
        if(pages%animalQueryParams.getLimit()!=0){
            return getPageList((pages/animalQueryParams.getLimit())+1);
        }

        return getPageList(pages/animalQueryParams.getLimit()) ;
    }

    private List<Integer> getPageList(int i){
        List<Integer> list=new ArrayList();
        for(int j=1;j<=i;j++){
            list.add(j);
        }

        return list;
    }


}
