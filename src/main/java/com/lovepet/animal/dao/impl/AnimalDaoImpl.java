package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.AnimalDao;
import com.lovepet.animal.dto.AnimalQueryParams;

import com.lovepet.animal.model.Shelter;
import com.lovepet.animal.rowmapper.AnimalRowmapper;
import com.lovepet.animal.rowmapper.ShelterRowmapper;
import com.lovepet.animal.util.PageContent;
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

    @Override
    public PageContent getAnimals(AnimalQueryParams animalQueryParams) {
        PageContent pageContent=new PageContent();
        Map<String,Object> map=new HashMap<>();
        boolean hasOtherQuery=false;
        String sql="select animal_id,animal_kind,animal_Variety,animal_sex,animal_bodytype,animal_colour,animal_age,animal_sterilization,animal_bacterin,animal_foundplace,animal_remark,album_file,shelter_name,shelter_address,shelter_tel,animal_opendate,animal_createtime,animal_update from shelter INNER join (select time.animal_id,animal_kind,animal_Variety,animal_sex,animal_bodytype,animal_colour,animal_age,animal_sterilization,animal_bacterin,animal_foundplace,animal_remark,album_file,animal_shelter_pkid,animal_opendate,animal_update,animal_createtime from animal INNER join time   on animal.animal_id= time.animal_id) animalJoinTime on shelter.animal_shelter_pkid=animalJoinTime.animal_shelter_pkid where 1=1 ";


        String shelterAndKindQuery="";
        if( !(animalQueryParams.getShelter().equals("所有收容所")) || !(animalQueryParams.getKind().equals("不分種類")) ){

            hasOtherQuery=true;
            if(!(animalQueryParams.getShelter().equals("所有收容所"))){
                shelterAndKindQuery=shelterAndKindQuery+" and shelter_name= :shelterName ";
                map.put("shelterName",animalQueryParams.getShelter());
            }
            if(!(animalQueryParams.getKind().equals("不分種類"))){
                shelterAndKindQuery=shelterAndKindQuery+" and animal_kind= :animalKind ";
                map.put("animalKind",animalQueryParams.getKind());
            }

            pageContent.setPages(getPages(animalQueryParams,map,shelterAndKindQuery));

     }
    if(!hasOtherQuery){
        pageContent.setPages(getPages(animalQueryParams,map,""));
    }


        String splitPage=" limit :limit offset :offset ";
        sql=sql+shelterAndKindQuery+splitPage;
        map.put("limit",animalQueryParams.getLimit());
        map.put("offset",animalQueryParams.getOffset());

        pageContent.setAnimals(namedParameterJdbcTemplate.query(sql,map,new AnimalRowmapper()));


        return pageContent;
    }


    private List<Integer> getPages(AnimalQueryParams animalQueryParams,Map map,String shelterAndKindQuery) {
        String sql="select count(*) from shelter INNER join (select time.animal_id,animal_kind,animal_Variety,animal_sex,animal_bodytype,animal_colour,animal_age,animal_sterilization,animal_bacterin,animal_foundplace,animal_remark,album_file,animal_shelter_pkid,animal_opendate,animal_update,animal_createtime from animal INNER join time   on animal.animal_id= time.animal_id) animalJoinTime on shelter.animal_shelter_pkid=animalJoinTime.animal_shelter_pkid where 1=1 ";



        Integer pages=namedParameterJdbcTemplate.queryForObject(sql+shelterAndKindQuery,map,Integer.class);
        if(pages%animalQueryParams.getLimit()!=0){
            return getPageList((pages/animalQueryParams.getLimit())+1);
        }

        return getPageList(pages/animalQueryParams.getLimit()) ;
    }

    @Override
    public List<Shelter> getShelter() {
        String sql="select shelter_name from shelter";
        Map<String,Object> map=new HashMap<>();
     List<Shelter>  dataList=  namedParameterJdbcTemplate.query(sql,map,new ShelterRowmapper());
        return dataList;
    }


    private List<Integer> getPageList(int i){
        List<Integer> list=new ArrayList();
        for(int j=1;j<=i;j++){
            list.add(j);
        }

        return list;
    }

}
