package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.PersonalAnimalDao;
import com.lovepet.animal.dto.PersonalAnimalQueryParams;
import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.rowmapper.PersonalAnimalRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class PersonalAnimalDaoImpl implements PersonalAnimalDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countPersonalAnimal(PersonalAnimalQueryParams personalAnimalQueryParams) {
        String sql = "SELECT count(*) FROM personal_animal WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, personalAnimalQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<PersonalAnimal> getPersonalAnimals(PersonalAnimalQueryParams personalAnimalQueryParams) {
//        String sql = "SELECT animal_id, animal_kind, animal_variety, animal_sex, animal_sterilization, animal_bacterin, " +
//                "image_url, description, created_date, last_modified_date " +
//                "FROM personal_animal WHERE 1=1";
        String sql = "SELECT * FROM personal_animal WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, personalAnimalQueryParams);

        //排序
        sql = sql + " ORDER BY " + personalAnimalQueryParams.getOrderBy() + " " + personalAnimalQueryParams.getSort();

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", personalAnimalQueryParams.getLimit());
        map.put("offset", personalAnimalQueryParams.getOffset());


        List<PersonalAnimal> personalAnimalList = namedParameterJdbcTemplate.query(sql, map, new PersonalAnimalRowmapper());

//        InputStream fis = null;
//        FileOutputStream fos = null;
//        for (PersonalAnimal personalAnimal : personalAnimalList) {
//            String url = personalAnimal.getImageUrl();
//
//            try {
//                URL imageUrl = new URL(url);
//                imageUrl.openConnection();
//                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
//                conn.connect();
//                fis = conn.getInputStream();
//                String path = String.format(System.getProperty("user.dir") +
//                        "\\src\\main\\resources\\static\\images\\publish\\%s", personalAnimal.getUserId() + "-" + personalAnimal.getAnimalId() + ".jpg");
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

//        for (PersonalAnimal personalAnimal : personalAnimalList) {
//            String sql1 = " update personal_animal set image_url=:url where animal_id=:aid and user_id=:uid ";
//            Map<String, Object> map1 = new HashMap<>();
//            map1.put("url", "/images/publish/" + personalAnimal.getUserId() + "-" + personalAnimal.getAnimalId() + ".jpg");
//            map1.put("aid", personalAnimal.getAnimalId());
//            map1.put("uid", personalAnimal.getUserId());
//
//            namedParameterJdbcTemplate.update(sql1, map1);
//
//        }


        return personalAnimalList;
    }

    @Override
    public PersonalAnimal getPersonalAnimalById(Integer personalAnimalId) {
//        String sql = "SELECT animal_id, animal_kind, animal_variety, animal_sex, animal_sterilization, animal_bacterin, " +
//                "image_url, description, created_date, last_modified_date " +
//                "FROM personal_animal WHERE animal_id = :personalAnimalId";
        String sql = "SELECT * FROM personal_animal WHERE animal_id = :personalAnimalId";

        Map<String, Object> map = new HashMap<>();
        map.put("personalAnimalId", personalAnimalId);

        List<PersonalAnimal> personalAnimalList = namedParameterJdbcTemplate.query(sql, map, new PersonalAnimalRowmapper());

        if (personalAnimalList.size() > 0) {
            return personalAnimalList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createPersonalAnimal(PersonalAnimalRequest personalAnimalRequest) {
        String sql = "INSERT INTO personal_animal(user_id, animal_name, animal_kind, animal_variety, animal_sex, animal_age, animal_bodysize, animal_color, animal_sterilization, animal_bacterin, image_url, area, description, created_date, last_modified_date) " +
                "VALUES (:userId, :animalName, :animalKind, :animalVariety, :animalSex, :animalAge, :animalBodysize, :animalColor, :animalSterilization, :animalBacterin, :imageUrl, :area, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", personalAnimalRequest.getUserId());
        map.put("animalName", personalAnimalRequest.getAnimalName());
        map.put("animalKind", personalAnimalRequest.getAnimalKind());
        map.put("animalVariety", personalAnimalRequest.getAnimalVariety());
        map.put("animalSex", personalAnimalRequest.getAnimalSex());
        map.put("animalAge", personalAnimalRequest.getAnimalAge());
        map.put("animalBodysize", personalAnimalRequest.getAnimalBodysize());
        map.put("animalColor", personalAnimalRequest.getAnimalColor());
        map.put("animalSterilization", personalAnimalRequest.getAnimalSterilization());
        map.put("animalBacterin", personalAnimalRequest.getAnimalBacterin());
        map.put("imageUrl", personalAnimalRequest.getImageUrl());
        map.put("area", personalAnimalRequest.getArea());
        map.put("description", personalAnimalRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int personalAnimalId = keyHolder.getKey().intValue();

        // 寫入圖片
        if (personalAnimalRequest.getAnimalPhoto() != null) {
            writePhoto(personalAnimalRequest, personalAnimalId);
        }
        // 取得 animal_id 後更新 image_url 資料 (檔案命名規則: /images/publish/{user_id}-{animal_id}.jpg)
        updatePersonalAnimal(personalAnimalId, personalAnimalRequest);

        return personalAnimalId;
    }

    @Override
    public void updatePersonalAnimal(Integer personalAnimalId, PersonalAnimalRequest personalAnimalRequest) {
        String sql = "UPDATE personal_animal SET user_id = :userId, animal_name = :animalName, animal_kind = :animalKind, animal_variety = :animalVariety, animal_sex = :animalSex, " +
                "animal_age = :animalAge, animal_bodysize = :animalBodysize, animal_color = :animalColor , " +
                "animal_sterilization = :animalSterilization, animal_bacterin = :animalBacterin, image_url = :imageUrl, area = :area, description = :description, " +
                "last_modified_date = :lastModifiedDate WHERE animal_id = :animalId";

        Map<String, Object> map = new HashMap<>();
        map.put("animalId", personalAnimalId);
        map.put("userId", personalAnimalRequest.getUserId());
        map.put("animalName", personalAnimalRequest.getAnimalName());
        map.put("animalKind", personalAnimalRequest.getAnimalKind());
        map.put("animalVariety", personalAnimalRequest.getAnimalVariety());
        map.put("animalSex", personalAnimalRequest.getAnimalSex());
        map.put("animalAge", personalAnimalRequest.getAnimalAge());
        map.put("animalBodysize", personalAnimalRequest.getAnimalBodysize());
        map.put("animalColor", personalAnimalRequest.getAnimalColor());
        map.put("animalSterilization", personalAnimalRequest.getAnimalSterilization());
        map.put("animalBacterin", personalAnimalRequest.getAnimalBacterin());
        map.put("imageUrl", "/images/publish/" + personalAnimalRequest.getUserId() + "-" + personalAnimalId + ".jpg");
        map.put("area", personalAnimalRequest.getArea());
        map.put("description", personalAnimalRequest.getDescription());
        map.put("lastModifiedDate", new Date());

        // 寫入圖片
        if (personalAnimalRequest.getAnimalPhoto() != null) {
            writePhoto(personalAnimalRequest, personalAnimalId);
        }

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deletePersonalAnimalById(Integer personalAnimalUserId, Integer personalAnimalId) {
        String sql = "DELETE FROM personal_animal WHERE user_id=:personalAnimalUserId  AND animal_id = :personalAnimalId";

        Map<String, Object> map = new HashMap<>();
        map.put("personalAnimalId", personalAnimalId);
        map.put("personalAnimalUserId", personalAnimalUserId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    private String addFilteringSql(String sql, Map<String, Object> map, PersonalAnimalQueryParams personalAnimalQueryParams) {
        if (personalAnimalQueryParams.getKind() != null) {
            sql = sql + " AND animal_kind = :animalKind";
            map.put("animalKind", personalAnimalQueryParams.getKind());
        }

        if (personalAnimalQueryParams.getSex() != null) {
            sql = sql + " AND animal_sex = :animalSex";
            map.put("animalSex", personalAnimalQueryParams.getSex());
        }

        if (personalAnimalQueryParams.getArea() != null) {
            sql = sql + " AND area = :area";
            map.put("area", personalAnimalQueryParams.getArea());
        }

        if (personalAnimalQueryParams.getId() != null) {
            sql = sql + " AND user_id=:userId ";
            map.put("userId", personalAnimalQueryParams.getId());
        }
        return sql;
    }

    private void writePhoto(PersonalAnimalRequest personalAnimalRequest, Integer personalAnimalId) {
        try {
            InputStream fis = personalAnimalRequest.getAnimalPhoto().getInputStream();

            // 專案路徑
            String pathSrc = String.format(System.getProperty("user.dir") +
                    "\\src\\main\\resources\\static\\images\\publish\\%s", personalAnimalRequest.getUserId() + "-" + personalAnimalId + ".jpg");

            // 編譯路徑
            String pathTarget = String.format(System.getProperty("user.dir") +
                    "\\target\\classes\\static\\images\\publish\\%s", personalAnimalRequest.getUserId() + "-" + personalAnimalId + ".jpg");

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


//下載
//    InputStream fis=null;
//    FileOutputStream fos=null;
//        for(PersonalAnimal personalAnimal:personalAnimalList){
//                String url=personalAnimal.getImageUrl();
//
//                try {
//                URL imageUrl = new URL(url);
//                imageUrl.openConnection();
//                HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
//                conn.connect();
//                fis= conn.getInputStream();
//                String   path =String.format("D:\\animal\\src\\main\\resources\\static\\images\\publish\\%s-%s.jpg",personalAnimal.getUserId(),personalAnimal.getAnimalId());
//                fos=new FileOutputStream(path);
//                byte[] buffer=new byte[1024];
//                int len;
//                while((len=fis.read(buffer))!=-1) {
//                fos.write(buffer,0,len);
//                }
//                fos.flush();
//                fis.close();
//                fos.close();
//                }catch (Exception e){
//
//                }
//                }


//改名
// for(PersonalAnimal personalAnimal:personalAnimalList){
//         String sql1=" update personal_animal set image_url=:url where animal_id=:aid and user_id=:uid ";
//         Map<String,Object> map1=new HashMap<>();
//        map1.put("url","/images/publish/"+personalAnimal.getUserId()+"-"+personalAnimal.getAnimalId()+".jpg");
//        map1.put("aid",personalAnimal.getAnimalId());
//        map1.put("uid",personalAnimal.getUserId());
//
//        namedParameterJdbcTemplate.update(sql1,map1);
//
//        }