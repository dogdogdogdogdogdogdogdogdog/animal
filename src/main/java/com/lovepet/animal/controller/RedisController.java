package com.lovepet.animal.controller;

import com.lovepet.animal.dto.RedisRequest;
import com.lovepet.animal.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PutMapping("/like")
    public String setLikeCount(@RequestBody RedisRequest redisRequest) {
        List<Map<String, Object>> redisList = null;
        //緩存格式 key= articleId::userId  value= 1 按過讚, 0 沒按過


        String articleId = redisRequest.getArticleId();
        String userId = redisRequest.getUserId();
        //拼接key
        String key = articleId + "::" + userId;

        System.out.println(articleId);
        System.out.println(userId);

        //判斷緩存是否存在
        boolean hasKey = redisUtils.checkHasKey(key);
        if (hasKey) {
            System.out.println("緩存存在");
            //讀取緩存的value
            String value = redisUtils.get(key);
            if (value=="1") {
                return "不能重複點讚";
            }else if (value=="0"){
                //更新緩存 value 為 1
                boolean isSuccess = redisUtils.getAndSet(key, "1");
                return "點讚";
            }
        } else {
            System.out.println("緩存不存在");

            try {
                String sql = "SELECT * FROM likes WHERE article_id = :articleId AND user_id = :userId";

                Map<String, Object> map = new HashMap<>();
                map.put("articleId", redisRequest.getArticleId());
                map.put("userId", redisRequest.getUserId());
                redisList = namedParameterJdbcTemplate.queryForList(sql, map);
                System.out.println(redisList.get(0).size());
                return "不能重複點讚";

            } catch (Exception e) {
                redisUtils.set(key, "1");

                return "點讚";
            }
        }
        return null;


    }
}
