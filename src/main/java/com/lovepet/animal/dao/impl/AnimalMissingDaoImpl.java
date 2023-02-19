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
import java.net.HttpURLConnection;
import java.net.URL;
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
        String sql = "SELECT * FROM animal_missing WHERE 1=1";

        List<AnimalMissing> list = namedParameterJdbcTemplate.query(sql, new AnimalMissingRowmapper());
        return list;
    }

    @Override
    public Integer countAnimalMissing(AnimalMissingQueryParams animalMissingQueryParams){
        String sql = "SELECT count(*) FROM animal_missing WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, animalMissingQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<AnimalMissing> getAnimalsMissing(AnimalMissingQueryParams animalMissingQueryParams){
        String sql = "SELECT * FROM animal_missing WHERE 1=1";

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


//                InputStream fis = null;
//        FileOutputStream fos = null;
//        for (AnimalMissing animalMissing : animalMissingList) {
//            String url = animalMissing.getImageUrl();
//
//            try {
//                URL imageUrl = new URL(url);
//                imageUrl.openConnection();
//                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
//                conn.connect();
//                fis = conn.getInputStream();
//                String path = String.format(System.getProperty("user.dir") +
//                        "\\src\\main\\resources\\static\\images\\missing\\%s", animalMissing.getUserId() + "-" + animalMissing.getAnimalId() + ".jpg");
//                fos=new FileOutputStream(path);
//                byte[] buffer = new byte[1024];
//                int len;
//                while ((len = fis.read(buffer)) != -1) {
//                    fos.write(buffer, 0, len);
//                }
//                fos.flush();
//                fis.close();
//                fos.close();
//            } catch (Exception e) {
//
//            }
//        }

//                for (AnimalMissing animalMissing : animalMissingList) {
//            String sql1 = " update animal_missing set image_url=:url where animal_id=:aid and user_id=:uid ";
//            Map<String, Object> map1 = new HashMap<>();
//            map1.put("url", "/images/missing/" + animalMissing.getUserId() + "-" + animalMissing.getAnimalId() + ".jpg");
//            map1.put("aid", animalMissing.getAnimalId());
//            map1.put("uid", animalMissing.getUserId());
//
//            namedParameterJdbcTemplate.update(sql1, map1);
//
//        }

        return animalMissingList;
    }

    @Override
    public AnimalMissing getAnimalMissingById(Integer animalMissingId){
        String sql = "SELECT * FROM animal_missing WHERE animal_id = :animalMissingId";

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
        String sql = "INSERT INTO animal_missing(user_id, name, kind, variety, bodysize, sex, color, age, description, image_url, area, missing_date, created_date)" +
                "VALUES (:userId, :name, :kind, :variety, :bodysize, :sex, :color, :age, :description, :imageUrl, :area, :missingDate, :createdDate)";

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
        map.put("area", animalMissingRequest.getArea());

        Date now = new Date();
        map.put("missingDate" , now);
        map.put("createdDate" , now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int animalMissingId = keyHolder.getKey().intValue();

        // 寫入圖片
        writePhoto(animalMissingRequest, animalMissingId);
        // 取得 animal_id 後更新 image_url 資料 (檔案命名規則: images/publish/{user_id}-{animal_id}.jpg)
        updateAnimalMissing(animalMissingId, animalMissingRequest);

        return animalMissingId;
    }

    @Override
    public void updateAnimalMissing(Integer animalMissingId, AnimalMissingRequest animalMissingRequest){
        String sql = "UPDATE animal_missing SET user_id = userId, name = :name , kind = :kind, variety = :variety, bodysize = :bodysize," +
                " sex = :sex, color = :color, age = :age, description = :description, image_url = :imageUrl," +
                " area = :area, missing_date = :missingDate WHERE animal_id = :animalId";
        writePhoto(animalMissingRequest, animalMissingId);
        Map<String, Object> map = new HashMap<>();
        map.put("animalId",animalMissingId);
        map.put("userId", animalMissingRequest.getUserId());
        map.put("name", animalMissingRequest.getName());
        map.put("kind", animalMissingRequest.getKind());
        map.put("variety", animalMissingRequest.getVariety());
        map.put("sex", animalMissingRequest.getSex());
        map.put("bodysize", animalMissingRequest.getBodysize());
        map.put("color", animalMissingRequest.getColor());
        map.put("age", animalMissingRequest.getAge());
        map.put("description", animalMissingRequest.getDescription());
        map.put("imageUrl", animalMissingRequest.getImageUrl());
        map.put("area", animalMissingRequest.getArea());
        map.put("missingDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }


    @Override
    public void deleteAnimalMissingById(Integer animalMissingUserId, Integer animalMissingId){
        String sql = "DELETE FROM animal_missing WHERE user_id=:animalMissingUserId AND animal_id = :animalMissingId";

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

        if (animalMissingQueryParams.getArea() != null) {
            sql = sql + " AND area = :area";
            map.put("area", animalMissingQueryParams.getArea());
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
                    "\\src\\main\\resources\\static\\images\\publish\\%s", animalMissingRequest.getUserId() + "-" + animalMissingId + ".jpg");

            // 編譯路徑
            String pathTarget = String.format(System.getProperty("user.dir") +
                    "\\target\\classes\\static\\images\\publish\\%s", animalMissingRequest.getUserId() + "-" + animalMissingId + ".jpg");

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



