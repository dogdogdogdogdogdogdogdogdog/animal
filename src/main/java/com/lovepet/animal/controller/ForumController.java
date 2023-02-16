package com.lovepet.animal.controller;

import com.lovepet.animal.dto.ForumArticleQueryParams;
import com.lovepet.animal.dto.ForumArticleRequest;
import com.lovepet.animal.model.ForumArticle;
import com.lovepet.animal.service.ForumArticleService;
import com.lovepet.animal.util.Page;
import com.lovepet.animal.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class ForumController {

    @Autowired
    private ForumArticleService forumArticleService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/forumArticles")//查詢所有文章
    public ResponseEntity<Page<ForumArticle>> getForumArticles(
            //查詢條件
            @RequestParam(required = false) Integer articleId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String search,

            //排序
            @RequestParam(defaultValue = "modified_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            //分頁
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,//一次查詢幾筆
            @RequestParam(defaultValue = "0") @Min(0) Integer offset//跳過幾筆資料
    ) {
        ForumArticleQueryParams forumArticleQueryParams = new ForumArticleQueryParams();
        forumArticleQueryParams.setArticleId(articleId);
        forumArticleQueryParams.setUserId(userId);
        forumArticleQueryParams.setType(type);
        forumArticleQueryParams.setSearch(search);
        forumArticleQueryParams.setOrderBy(orderBy);
        forumArticleQueryParams.setSort(sort);
        forumArticleQueryParams.setLimit(limit);
        forumArticleQueryParams.setOffset(offset);

        //取得list
        List<ForumArticle> forumArticleList = forumArticleService.getForumArticles(forumArticleQueryParams);

        //取的總筆數
        Integer total = forumArticleService.countForumArticle(forumArticleQueryParams);

        //分頁
        Page<ForumArticle> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(forumArticleList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/forumArticles/{forumArticleId}")//查詢指定文章id(單筆)
    public ResponseEntity<ForumArticle> getForumArticle(@PathVariable Integer forumArticleId) {
        ForumArticle forumArticle = forumArticleService.getForumArticleById(forumArticleId);

        if (forumArticle != null) {
            return ResponseEntity.status(HttpStatus.OK).body(forumArticle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/forumArticles")//發布文章
    public ResponseEntity<ForumArticle> createPersonalAnimal(@RequestBody @Valid ForumArticleRequest forumArticleRequest) {
        Integer forumArticleId = forumArticleService.createForumArticle(forumArticleRequest);

        ForumArticle forumArticle = forumArticleService.getForumArticleById(forumArticleId);

        return ResponseEntity.status(HttpStatus.CREATED).body(forumArticle);
    }

    @PutMapping("/forumArticles/{forumArticleId}")//編輯文章
    public ResponseEntity<ForumArticle> updateForumArticle(@PathVariable Integer forumArticleId,
                                                           @RequestBody @Valid ForumArticleRequest forumArticleRequest) {
        //檢查forumArticleId 是否存在
        ForumArticle forumArticle = forumArticleService.getForumArticleById(forumArticleId);

        if (forumArticle == null) {//找不到回傳404 NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改文章內容
        forumArticleService.updateForumArticle(forumArticleId, forumArticleRequest);

        ForumArticle updatedForumArticle = forumArticleService.getForumArticleById(forumArticleId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedForumArticle);
    }

    @DeleteMapping("/forumArticles/{forumArticleUserId}/{forumArticleId}")//刪除文章
    public ResponseEntity<?> deleteForumArticle(@PathVariable Integer forumArticleUserId, @PathVariable Integer forumArticleId) {
        forumArticleService.deleteForumArticleById(forumArticleUserId, forumArticleId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("/like")
    public ResponseEntity<?> setLikeCount() {

        /**
         * 插入缓存数据
         */

        boolean var = redisUtils.set("redis_key", "redis_vale");
        System.out.println(var);

        /**
         * 读取缓存数据
         */

        String value = redisUtils.get("redis_key");
        System.out.println(value);


//        System.out.println("點讚");
//        boolean isSuccess = forumArticleService.storeUserTrends("blog_like", articleId, userId);
//        if (isSuccess) {
//            System.out.println("點讚成功");
//            return ResponseEntity.status(HttpStatus.OK).build();
//        }
//        System.out.println("點讚失敗");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
