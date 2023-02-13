package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.MissingDao;
import com.lovepet.animal.dto.MissingAnimalRequest;
import com.lovepet.animal.dto.MissingAnimalsQueryParams;
import com.lovepet.animal.model.MissingData;
import com.lovepet.animal.rowmapper.MissingDataRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class MissingDaoImpl implements MissingDao {
    @Autowired
    private NamedParameterJdbcTemplate animalJdbcTemplate;

    @Override
    public void createMissing(MissingAnimalRequest missingAnimalRequest) {
        String sql = " insert into missing_animal(user_id,kind,variety,sex,body_shape,color,age,missing_date,missing_place,remark,photo_url,publish_date) value(:user_id,:kind,:variety,:sex,:body_shape,:color,:age,:missing_date,:missing_place,:remark,:photo_url,:publish_date) ";
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", missingAnimalRequest.getUserId());
        map.put("kind", missingAnimalRequest.getMissingKind());
        map.put("variety", missingAnimalRequest.getMissingVariety());
        map.put("sex", missingAnimalRequest.getMissingSex());
        map.put("body_shape", missingAnimalRequest.getMissingBodyShape());
        map.put("color", missingAnimalRequest.getMissingColor());
        map.put("age", missingAnimalRequest.getMissingAge());
        map.put("missing_date", missingAnimalRequest.getMissingDate());
        map.put("missing_place", missingAnimalRequest.getMissingPlace());
        map.put("remark", missingAnimalRequest.getMissingRemark());
        map.put("photo_url", missingAnimalRequest.getPhotoUrl());
        Date now = new Date();
        map.put("publish_date", now);


        KeyHolder keyHolder = new GeneratedKeyHolder();

        animalJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int missingAnimalId = keyHolder.getKey().intValue();


        writePhoto(missingAnimalRequest,missingAnimalId);
    }

    @Override
    public List<MissingData> getMissingAnimals(MissingAnimalsQueryParams missingAnimalsQueryParams) {
        String sql = " select kind,variety,sex,body_shape,color,age,missing_date,missing_place,remark,photo_url from missing_animal where 1=1  ";
        Map<String, Object> map = new HashMap<>();

        addFilterSql(sql,map,missingAnimalsQueryParams);


        sql+=" order by "+missingAnimalsQueryParams.getOrderBy()+" "+missingAnimalsQueryParams.getSort();
        sql+=" limit "+missingAnimalsQueryParams.getLimit()+" "+" offset "+missingAnimalsQueryParams.getOffset();

        List<MissingData> missingDataList = animalJdbcTemplate.query(sql, map, new MissingDataRowmapper());

        if (missingDataList.size() > 0) {
            return missingDataList;
        }else {
            return null;
        }
    }

    @Override
    public List<MissingData> getMissingById(Integer id) {
        String sql = " select kind,variety,sex,body_shape,color,age,missing_date,missing_place,remark,photo_url from missing_animal where user_id=:userId ";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", id);

        List<MissingData> missingDataList = animalJdbcTemplate.query(sql, map, new MissingDataRowmapper());

        if (missingDataList.size() > 0) {
            return missingDataList;
        }else {
            return null;
        }
    }

    @Override
    public Integer countMissingAnimals(MissingAnimalsQueryParams missingAnimalsQueryParams) {
        String sql = " select count(*) from missing_animal where 1=1  ";
        Map<String, Object> map = new HashMap<>();

        addFilterSql(sql,map,missingAnimalsQueryParams);


        Integer count = animalJdbcTemplate.queryForObject(sql,map,Integer.class);


        return count;
    }

    private String addFilterSql(String sql,Map<String,Object> map,MissingAnimalsQueryParams missingAnimalsQueryParams){
        if(missingAnimalsQueryParams.getKind()!=null){
            sql+=" kind=:kind ";
            map.put("kind",missingAnimalsQueryParams.getKind());
        }
        if(missingAnimalsQueryParams.getSex()!=null){
            sql+=" sex:sex ";
            map.put("sex",missingAnimalsQueryParams.getSex());
        }

        return sql;
    }
    private void writePhoto(MissingAnimalRequest missingAnimalRequest, Integer personalAnimalId){
        try {
            InputStream fis = missingAnimalRequest.getMissingAnimalPhoto().getInputStream();
            String path = String.format("D:/animal/src/main/resources/static/images/missing/%s", missingAnimalRequest.getUserId()+"-"+personalAnimalId + ".jpg");
            FileOutputStream fos = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
