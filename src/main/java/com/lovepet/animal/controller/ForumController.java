package com.lovepet.animal.controller;

import com.lovepet.animal.dto.ForumArticleQueryParams;
import com.lovepet.animal.model.ForumArticle;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.service.ForumArticleService;
import com.lovepet.animal.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class ForumController {

    @Autowired
    private ForumArticleService forumArticleService;

    @GetMapping("/forumArtices")//查詢所有文章
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

    @GetMapping("/forumArtices/{forumArticeId}")//查詢個人收容所資料(單筆)
    public ResponseEntity<ForumArticle> getForumArticle(@PathVariable Integer forumArticeId) {
        ForumArticle forumArticle = forumArticleService.getForumArticleById(forumArticeId);

        if (forumArticle != null) {
            return ResponseEntity.status(HttpStatus.OK).body(forumArticle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
