package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.AnimalMissingDao;
import com.lovepet.animal.dto.AnimalMissingQueryParams;
import com.lovepet.animal.dto.AnimalMissingRequest;
import com.lovepet.animal.model.AnimalMissing;
import com.lovepet.animal.rowmapper.AnimalMissingRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimalMissingDaoImpl implements AnimalMissingDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<AnimalMissing> getAnimalsMissingComboBox(){
        String sql = "SELECT * FROM missing_animal WHERE 1=1";

        List<AnimalMissing> list = namedParameterJdbcTemplate.query(sql, new AnimalMissingRowmapper());
        return list;
    }

    @Override
    public Integer countAnimalMissing(AnimalMissingQueryParams animalMissingQueryParams){
        String sql = "SELECT count(*) FROM missing_animal WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, animalMissingQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<AnimalMissing> getAnimalsMissing(AnimalMissingQueryParams animalMissingQueryParams){
        String sql = "SELECT * FROM missing_animal WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, animalMissingQueryParams);

        //排序
        sql = sql + " ORDER BY " + animalMissingQueryParams.getOrderBy() + " " + animalMissingQueryParams.getSort();

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", animalMissingQueryParams.getLimit());
        map.put("offset", animalMissingQueryParams.getOffset());

        List<AnimalMissing> animalMissingList = namedParameterJdbcTemplate.query(sql, map, new AnimalMissingRowmapper());

        return animalMissingList;
    }

    @Override
    public AnimalMissing getAnimalMissingById(Integer animalMissingId){
        String sql = "SELECT * FROM missing_animal WHERE animal_id = :animalMissingId";

        Map<String, Object> map = new HashMap<>();
        map.put("animalMissingId", animalMissingId);

        List<AnimalMissing> animalMissingList = namedParameterJdbcTemplate.query(sql, map, new AnimalMissingRowmapper());

        if(animalMissingList.size() > 0){
            return animalMissingList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public  Integer createAnimalMissing(AnimalMissingRequest animalMissingRequest){
        String sql = "INSERT INTO missing_animal(user_id, name, kind, variety, bodysize, sex, color, age, description, image_url, missing_area, missing_date, created_date)" +
                "VALUES (:userId, :name, :kind, :variety, :bodysize, :sex, :color, :age, :description, :imageUrl, :missingArea, :missingDate, :createdDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", animalMissingRequest.getUserId());
        map.put("name", animalMissingRequest.getName());
        map.put("kind", animalMissingRequest.getKind());
        map.put("variety", animalMissingRequest.getVariety());
        map.put("bodysize", animalMissingRequest.getBodysize());
        map.put("sex", animalMissingRequest.getSex());
        map.put("color", animalMissingRequest.getColor());
        map.put("age", animalMissingRequest.getAge());
        map.put("description", animalMissingRequest.getDescription());
        map.put("imageUrl", animalMissingRequest.getImageUrl());
        map.put("missingArea", animalMissingRequest.getMissingArea());

        Date now = new Date();
        map.put("missingDate" , now);
        map.put("createdDate" , now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int animalMissingId = keyHolder.getKey().intValue();

            writePhoto(animalMissingRequest, animalMissingId);

        return animalMissingId;
    }

    @Override
    public void updateAnimalMissing(Integer animalMissingId, AnimalMissingRequest animalMissingRequest){
        String sql = "UPDATE missing_animal SET user_id = userId, name = :name , kind = :kind, variety = :variety, bodysize = :bodysize," +
                " sex = :sex, color = :color, age = :age, description = :description, image_url = :imageUrl," +
                " missing_area = :missingArea, missing_date = :missingDate, created_date = :createdDate WHERE animal_id = :animalId";
        writePhoto(animalMissingRequest, animalMissingId);
        Map<String, Object> map = new HashMap<>();
        map.put("animalId",animalMissingId);
        map.put("userId", animalMissingRequest.getUserId());
        map.put("name", animalMissingRequest.getName());
        map.put("kind", animalMissingRequest.getKind());
        map.put("variety", animalMissingRequest.getVariety());
        map.put("sex", animalMissingRequest.getSex());
        map.put("color", animalMissingRequest.getColor());
        map.put("age", animalMissingRequest.getAge());
        map.put("description", animalMissingRequest.getDescription());
        map.put("imageUrl", animalMissingRequest.getImageUrl());
        map.put("missingArea", animalMissingRequest.getMissingArea());
        map.put("missingDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }


    @Override
    public void deleteAnimalMissingById(Integer animalMissingUserId, Integer animalMissingId){
        String sql = "DELETE FROM missing_animal WHERE user_id=:animalMissingUserId AND animal_id = :animalMissingId";

        Map<String, Object> map = new HashMap<>();
        map.put("animalMissingId", animalMissingId);
        map.put("animalMissingUserId", animalMissingUserId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    private String addFilteringSql(String sql, Map<String, Object> map, AnimalMissingQueryParams animalMissingQueryParams) {
        if (animalMissingQueryParams.getKind() != null) {
            sql = sql + " AND kind = :kind";
            map.put("kind", animalMissingQueryParams.getKind());
        }

        if (animalMissingQueryParams.getSex() != null) {
            sql = sql + " AND sex = :sex";
            map.put("sex", animalMissingQueryParams.getSex());
        }

        if (animalMissingQueryParams.getMissingArea() != null) {
            sql = sql + " AND missing_area = :missingArea";
            map.put("missingArea", animalMissingQueryParams.getMissingArea());
        }

        if(animalMissingQueryParams.getId()!=null){
            sql= sql + " AND user_id=:userId ";
            map.put("userId",animalMissingQueryParams.getId());
        }
        return sql;
    }

    private void writePhoto(AnimalMissingRequest animalMissingRequest, Integer animalMissingId) {
        try {
            InputStream fis = animalMissingRequest.getAnimalPhoto().getInputStream();

            // 專案路徑
            String pathSrc = String.format(System.getProperty("user.dir") +
                    "\\src\\main\\resources\\static\\images\\publish\\%s", animalMissingRequest.getUserId() + "-" + animalMissingRequest + ".jpg");

            // 編譯路徑
            String pathTarget = String.format(ClassUtils.getDefaultClassLoader().getResource("").getPath() +
                    "static/images/publish/%s", animalMissingRequest.getUserId() + "-" + animalMissingRequest + ".jpg");

            FileOutputStream fosSrc = new FileOutputStream(pathSrc);
            FileOutputStream fosTarget = new FileOutputStream(pathTarget);

            byte[] buffer = new byte[1024];
            int len;

            while ((len = fis.read(buffer)) != -1) {
                fosSrc.write(buffer, 0, len);
                fosTarget.write(buffer, 0, len);
            }

            fosSrc.flush();
            fosSrc.close();
            fosTarget.flush();
            fosTarget.close();

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



