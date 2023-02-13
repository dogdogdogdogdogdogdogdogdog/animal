package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.PublicAnimalDao;
import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.rowmapper.AnimalRowmapper;
import com.lovepet.animal.util.PublicPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PublicAnimalDaoImpl implements PublicAnimalDao {
        @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public PublicPage<AnimalData>  getPublicAnimals(AnimalQueryParams animalQueryParams) {
        String sql = "SELECT animal_id, animal_kind, animal_Variety, animal_sex, animal_colour, animal_bodytype, animal_age, animal_sterilization, " +
                "animal_bacterin, animal_foundplace, animal_remark, album_file, shelter_name, shelter_address, shelter_tel, animal_opendate, " +
                "animal_createtime, animal_update, animal_status animal_area FROM public_animal WHERE 1=1 ";
        HashMap<String,Object> map=new HashMap<>();
        String shelterAndKindQuery = "";
        if (!(animalQueryParams.getShelter().equals("所有收容所")) || !(animalQueryParams.getKind().equals("不分種類"))) {
            if (!(animalQueryParams.getShelter().equals("所有收容所"))) {
                shelterAndKindQuery = shelterAndKindQuery + " and shelter_name= :shelterName ";
                map.put("shelterName", animalQueryParams.getShelter());
            }
            if (!(animalQueryParams.getKind().equals("不分種類"))) {
                shelterAndKindQuery = shelterAndKindQuery + " and animal_kind= :animalKind ";
                map.put("animalKind", animalQueryParams.getKind());
            }


        }
        Integer pages=getPages( animalQueryParams,map,shelterAndKindQuery);

        String splitPage = " limit :limit offset :offset ";
        sql = sql + shelterAndKindQuery + splitPage;
        map.put("limit", animalQueryParams.getLimit());
        map.put("offset",(animalQueryParams.getPage()-1)* animalQueryParams.getLimit());

       List<AnimalData> animals = namedParameterJdbcTemplate.query(sql, map, new AnimalRowmapper());

        PublicPage<AnimalData> publicPage=new PublicPage<>();
       publicPage.setPublicAnimals(animals);
       publicPage.setPage(showPage(animalQueryParams.getPage(),pages));
        return publicPage;
    }

    private Integer getPages(AnimalQueryParams animalQueryParams, Map map, String shelterAndKindQuery) {
        //String sql="select count(*) from shelter INNER join (select time.animal_id,animal_kind,animal_Variety,animal_sex,animal_bodytype,animal_colour,animal_age,animal_sterilization,animal_bacterin,animal_foundplace,animal_remark,album_file,animal_shelter_pkid,animal_opendate,animal_update,animal_createtime from animal INNER join time   on animal.animal_id= time.animal_id) animalJoinTime on shelter.animal_shelter_pkid=animalJoinTime.animal_shelter_pkid where 1=1 ";
        String sql = "SELECT COUNT(*) FROM public_animal WHERE 1=1";


        Integer pages = namedParameterJdbcTemplate.queryForObject(sql + shelterAndKindQuery, map, Integer.class);
        if (pages % animalQueryParams.getLimit() != 0) {
            return (pages / animalQueryParams.getLimit()) + 1;
        }

        return pages / animalQueryParams.getLimit();
    }


    private  List<String> showPage(int curPage, int pages){
        ArrayList<String> ls=new ArrayList<>();
        for(int i=1;i<pages+1;i++){
            ls.add(String.valueOf(i));
        }
        int p1=0;
        int p2=6;

        if(pages>6){
            if(curPage>1){
                p1=curPage-1;
                p2=p2+p1;
                int subLine=pages/2;
                if(p1>subLine) {
                    if (p2 > pages - 1) {
                        p2 = pages;
                        if(p2-p1<6){
                            int gap=p2-p1;

                            p1=p1-(6-gap);

                        }
                    }

                }else {
                    if (p1 <=0) {
                        p1 = 0;
                    }
                }
            }

        }else{
            if(pages>0){
                p2=pages;
            }
        }
        return  ls.subList(p1,p2);
    }
}
