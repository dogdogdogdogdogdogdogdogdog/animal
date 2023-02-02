package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.PublishDao;
import com.lovepet.animal.dto.PublishAnimalRequest;
import com.lovepet.animal.model.PublishData;
import com.lovepet.animal.rowmapper.PublishDataRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class PublishDaoImpl implements PublishDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createPublish(PublishAnimalRequest publishAnimalRequest) {
        String sql="insert into publish_animal(user_id,kind,variety,sex,body_shape,color,age,ligation,address,remark,photo_url,created_date,last_modified_date) values(:user_id,:kind,:variety,:sex,:body_shape,:color,:age,:ligation,:address,:remark,:photo_url,:created_date,:last_modified_date) ";

        Map<String,Object> map=new HashMap<>();
        map.put("user_id",publishAnimalRequest.getId());
        map.put("kind",publishAnimalRequest.getKind());
        map.put("variety",publishAnimalRequest.getVariety());
        map.put("sex",publishAnimalRequest.getSex());
        map.put("body_shape",publishAnimalRequest.getBodyShape());
        map.put("color",publishAnimalRequest.getColor());
        map.put("age",publishAnimalRequest.getAge());
        map.put("ligation",publishAnimalRequest.getLigation());
        map.put("address",publishAnimalRequest.getAddress());
        map.put("remark",publishAnimalRequest.getRemark());
        map.put("photo_url",publishAnimalRequest.getId());
        Date now = new Date();
        map.put("created_date",now);
        map.put("last_modified_date",now);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int publish_id=keyHolder.getKey().intValue();

        try {
            InputStream fis=publishAnimalRequest.getAnimalPhoto().getInputStream();
            String   path =String.format("D:/animal/src/main/resources/static/images/publish/%s",publishAnimalRequest.getId()+"-"+publish_id+".jpg");
            FileOutputStream fos=new FileOutputStream(path);
            byte[] buffer=new byte[1024];
            int len;
            while((len=fis.read(buffer))!=-1) {
                fos.write(buffer,0,len);
            }
            fos.flush();
            fis.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }





    }

    @Override
    public void updatePublish(PublishAnimalRequest publishAnimalRequest) {
        String sql=" update publish_animal set ";
        Map<String,Object> map=new HashMap<>();
        Class clazz=  publishAnimalRequest.getClass();
        Field[] fileds= clazz.getDeclaredFields();
        String addUpdateWhichColum="";
        for(Field f:fileds){
            f.setAccessible(true);
            try {

                if (f.get(publishAnimalRequest)!=null){

                    if(f.getName().equals("kind")){
                        String addUpdateValue="kind=:kind,";
                        addUpdateWhichColum+=addUpdateValue;
                        map.put("kind",f.get(publishAnimalRequest));
                        continue;
                    }
                    else if(f.getName().equals("variety")){
                        String addUpdateValue="variety=:variety,";
                        addUpdateWhichColum+=addUpdateValue;
                        map.put("variety",f.get(publishAnimalRequest));
                        continue;
                    }
                    else if(f.getName().equals("sex")){
                        String addUpdateValue="sex=:sex,";
                        addUpdateWhichColum+=addUpdateValue;
                        map.put("sex",f.get(publishAnimalRequest));
                        continue;
                    }
                    else if(f.getName().equals("bodyShape")){
                        String addUpdateValue="body_shape=:bodyShape,";
                        addUpdateWhichColum+=addUpdateValue;
                        map.put("bodyShape",f.get(publishAnimalRequest));
                        continue;
                    }
                    else if(f.getName().equals("color")){
                        String addUpdateValue="color=:color,";
                        addUpdateWhichColum+=addUpdateValue;
                        map.put("color",f.get(publishAnimalRequest));
                        continue;
                    }
                    else if(f.getName().equals("age")){
                        String addUpdateValue="age=:age,";
                        addUpdateWhichColum+=addUpdateValue;
                        map.put("age",f.get(publishAnimalRequest));
                        continue;
                    }
                    else if(f.getName().equals("ligation")){
                        String addUpdateValue="ligation=:ligation,";
                        addUpdateWhichColum+=addUpdateValue;
                        map.put("ligation",f.get(publishAnimalRequest));
                        continue;
                    }
                    else if(f.getName().equals("address")){
                        String addUpdateValue="address=:address,";
                        addUpdateWhichColum+=addUpdateValue;
                        map.put("address",f.get(publishAnimalRequest));
                        continue;
                    }
                    else if(f.getName().equals("remark")){
                        String addUpdateValue="remark=:remark,";
                        addUpdateWhichColum+=addUpdateValue;
                        map.put("remark",f.get(publishAnimalRequest));
                        continue;
                    }else if(f.getName().equals("animalPhoto")){
                        try {
                            InputStream fis=publishAnimalRequest.getAnimalPhoto().getInputStream();
                            String   path =String.format("D:/animal/src/main/resources/static/images/publish/%s",publishAnimalRequest.getId()+"-"+ publishAnimalRequest.getItem()+".jpg");
                            FileOutputStream fos=new FileOutputStream(path);
                            byte[] buffer=new byte[1024];
                            int len;
                            while((len=fis.read(buffer))!=-1) {
                                fos.write(buffer,0,len);
                            }
                            fos.flush();
                            fis.close();
                            fos.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }
                }


            }catch (IllegalAccessException e){

            }
        }

        String condition=" where user_id=:userId and publish_id=:publishId ";
        map.put("userId",publishAnimalRequest.getId());
        map.put("publishId",publishAnimalRequest.getItem());
        if(!addUpdateWhichColum.equals("")) {
            namedParameterJdbcTemplate.update(sql + addUpdateWhichColum.substring(0, addUpdateWhichColum.length() - 1) + condition, map);
                 }
        }

    @Override
    public List<PublishData> getPublishById(Integer id) {
        Map<String,Object> map=new HashMap<>();
        String sql;
        if(id!=null){
           sql=" select kind,variety,sex,body_shape,color,age,ligation,address,remark,photo_url,publish_id from publish_animal where user_id=:userId " ;

            map.put("userId",id);
        }else{
            sql="select kind,variety,sex,body_shape,color,age,ligation,address,remark,photo_url,publish_id,name,tel from publish_animal inner join user u on publish_animal.user_id = u.user_id";
        }


       List<PublishData> publishDataList= namedParameterJdbcTemplate.query(sql,map,new PublishDataRowmapper());
        if  (publishDataList.size()>0) return publishDataList;
        return null;
    }

    @Override
    public void delPublish(Integer userId, Integer item) {
        String sql=" DELETE FROM publish_animal WHERE user_id=:userId and   publish_id=:item ";
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("item",item);


        File file=new File(String.format("D:/animal/src/main/resources/static/images/publish/%s-%s.jpg",userId,item));
        boolean fileStatus=(file.exists() ? file.delete():false );

        namedParameterJdbcTemplate.update(sql,map);





    }
}
