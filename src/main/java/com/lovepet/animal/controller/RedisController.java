package com.lovepet.animal.controller;

import com.lovepet.animal.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;
    @GetMapping("/like")
    public ResponseEntity<?> setLikeCount() {

        /**
         * 插入缓存数据
         */
        System.out.println("點讚");
        boolean isSuccess = redisUtils.set("redis_key", "redis_vale");
        System.out.println(isSuccess);

        /**
         * 读取缓存数据
         */

        String value = redisUtils.get("redis_key");
        System.out.println(value);


//
//        boolean isSuccess = forumArticleService.storeUserTrends("blog_like", articleId, userId);
//        if (isSuccess) {
//            System.out.println("點讚成功");
//            return ResponseEntity.status(HttpStatus.OK).build();
//        }
//        System.out.println("點讚失敗");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}
