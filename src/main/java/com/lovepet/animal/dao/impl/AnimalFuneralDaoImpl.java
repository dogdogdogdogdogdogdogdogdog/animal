package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.AnimalFuneralDao;
import com.lovepet.animal.dto.AnimalFuneralQueryParams;
import com.lovepet.animal.dto.AnimalFuneralRequest;
import com.lovepet.animal.model.AnimalFuneral;
import com.lovepet.animal.rowmapper.AnimalFuneralRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimalFuneralDaoImpl implements AnimalFuneralDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countFuneral(AnimalFuneralQueryParams animalFuneralQueryParams) {
        String sql = "SELECT count(*) FROM animal_funeral WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, animalFuneralQueryParams);

        // 將 count 值轉換為 Integer 類型的返回值
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<AnimalFuneral> getFunerals(AnimalFuneralQueryParams animalFuneralQueryParams) {
        String sql = "SELECT id, agent, tel, area, address, link, service FROM animal_funeral WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, animalFuneralQueryParams);

        // 分頁
        sql += " LIMIT :limit OFFSET :offset";
        map.put("limit", animalFuneralQueryParams.getLimit());
        map.put("offset", animalFuneralQueryParams.getOffset());

        List<AnimalFuneral> animalFuneralList = namedParameterJdbcTemplate.query(sql, map, new AnimalFuneralRowmapper());

        return animalFuneralList;
    }

    private String addFilteringSql(String sql, Map<String, Object> map, AnimalFuneralQueryParams animalFuneralQueryParams) {
        if (animalFuneralQueryParams.getArea() != null) {
            sql += " AND area = :area";
            map.put("area", animalFuneralQueryParams.getArea());
        }

        return sql;
    }

    @Override
    public AnimalFuneral getFuneralById(Integer funeralId) {
        String sql = "SELECT id, agent, tel, area, address, link, service FROM animal_funeral WHERE id = :funeralId";

        Map<String, Object> map = new HashMap<>();
        map.put("funeralId", funeralId);

        List<AnimalFuneral> animalFuneralList = namedParameterJdbcTemplate.query(sql, map, new AnimalFuneralRowmapper());

        if (animalFuneralList.size() > 0) {
            return animalFuneralList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createFuneral(AnimalFuneralRequest animalFuneralRequest) {
        String sql = "INSERT INTO animal_funeral(agent, tel, area, address, link, service) VALUES (:agent, :tel, :area, :address, :link, :service)";

        Map<String, Object> map = new HashMap<>();
        map.put("agent", animalFuneralRequest.getAgent());
        map.put("tel", animalFuneralRequest.getTel());
        map.put("area", animalFuneralRequest.getArea());
        map.put("address", animalFuneralRequest.getAddress());
        map.put("link", animalFuneralRequest.getLink());
        map.put("service", animalFuneralRequest.getService());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int funeralId = keyHolder.getKey().intValue();

        return funeralId;
    }

    @Override
    public void updateFuneral(Integer funeralId, AnimalFuneralRequest animalFuneralRequest) {
        String sql = "UPDATE animal_funeral SET agent = :agent, tel = :tel, area = :area, address = :address, link = :link, service = :service WHERE id = :funeralId";

        Map<String, Object> map = new HashMap<>();
        map.put("funeralId", funeralId);
        map.put("agent", animalFuneralRequest.getAgent());
        map.put("tel", animalFuneralRequest.getTel());
        map.put("area", animalFuneralRequest.getArea());
        map.put("address", animalFuneralRequest.getAddress());
        map.put("link", animalFuneralRequest.getLink());
        map.put("service", animalFuneralRequest.getService());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteFuneralById(Integer funeralId) {
        String sql = "DELETE FROM animal_funeral WHERE id = :funeralId";

        Map<String, Object> map = new HashMap<>();
        map.put("funeralId", funeralId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
