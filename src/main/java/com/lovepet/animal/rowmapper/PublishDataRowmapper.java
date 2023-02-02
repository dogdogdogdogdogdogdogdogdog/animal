package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.PublishData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublishDataRowmapper implements RowMapper {


    @Override
    public PublishData mapRow(ResultSet resultSet, int i) throws SQLException {

        PublishData publishData=new PublishData();
        publishData.setKind(resultSet.getString("kind"));
        publishData.setVariety(resultSet.getString("variety"));
        publishData.setSex(resultSet.getString("sex"));
        publishData.setBodyShape(resultSet.getString("body_shape"));
        publishData.setColor(resultSet.getString("color"));
        publishData.setAge(resultSet.getString("age"));
        publishData.setLigation(resultSet.getString("ligation"));
        publishData.setAddress(resultSet.getString("address"));
        publishData.setRemark(resultSet.getString("remark"));
        publishData.setPhoto_url(resultSet.getString("photo_url")+"-"+resultSet.getString("publish_id"));
        publishData.setItem(resultSet.getInt("publish_id"));
try {
 publishData.setTel(resultSet.getString("tel"));
 publishData.setName(resultSet.getString("name"));

}catch (SQLException e){

}
        return publishData;
    }
}
