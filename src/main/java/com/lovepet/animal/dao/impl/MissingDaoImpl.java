package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.MissingDao;
import com.lovepet.animal.dto.MissingAnimalRequest;
import com.lovepet.animal.model.MissingData;
import com.lovepet.animal.rowmapper.MissingDataRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class MissingDaoImpl implements MissingDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createMissing(MissingAnimalRequest missingAnimalRequest) {
        String sql = " insert into missing_animal(user_id,kind,variety,sex,body_shape,color,age,missing_date,missing_place,remark,photo_url,publish_date) value(:user_id,:kind,:variety,:sex,:body_shape,:color,:age,:missing_date,:missing_place,:remark,:photo_url,:publish_date) ";
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", missingAnimalRequest.getMissingId());
        map.put("kind", missingAnimalRequest.getMissingKind());
        map.put("variety", missingAnimalRequest.getMissingVariety());
        map.put("sex", missingAnimalRequest.getMissingSex());
        map.put("body_shape", missingAnimalRequest.getMissingBodyShape());
        map.put("color", missingAnimalRequest.getMissingColor());
        map.put("age", missingAnimalRequest.getMissingAge());
        map.put("missing_date", missingAnimalRequest.getMissingDate());
        map.put("missing_place", missingAnimalRequest.getMissingPlace());
        map.put("remark", missingAnimalRequest.getMissingRemark());
        map.put("photo_url", missingAnimalRequest.getMissingId());
        Date now = new Date();
        map.put("publish_date", now);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<MissingData> getMissingById(Integer id) {
        String sql = " select kind,variety,sex,body_shape,color,age,missing_date,missing_place,remark,photo_url from missing_animal where user_id=:userId ";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", id);

        List<MissingData> missingDataList = namedParameterJdbcTemplate.query(sql, map, new MissingDataRowmapper());

        if (missingDataList.size() > 0) return missingDataList;
        return null;
    }
}
