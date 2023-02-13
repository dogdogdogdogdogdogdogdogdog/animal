package com.lovepet.animal.controller;


import com.lovepet.animal.dto.ForumData;
import com.lovepet.animal.dto.UserForumQueryParams;
import com.lovepet.animal.model.UserForum;
import com.lovepet.animal.service.ForumService;
import com.lovepet.animal.util.DiscussPage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class PersonalForum {

    @Autowired
    ForumService forumService;

    @PostMapping("/personalForum")
    public ResponseEntity createForum(@RequestBody ForumData forumData){

            forumService.createForum(forumData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/personalForums")
    public ResponseEntity<DiscussPage<UserForum>> getForums( //排序
                                                             @RequestParam(defaultValue = "create_date") String orderBy,
                                                             @RequestParam(defaultValue = "desc") String sort,

                                                             //分頁
                                                             @RequestParam(defaultValue = "12") @Max(1000) @Min(0) Integer limit,//一次查詢幾筆

                                                             @RequestParam(defaultValue = "1") @Min(0) Integer page ){
        UserForumQueryParams userForumQueryParams=new UserForumQueryParams();
        userForumQueryParams.setLimit(limit);

        userForumQueryParams.setOrderBy(orderBy);
        userForumQueryParams.setSort(sort);
        userForumQueryParams.setPage(page);

        DiscussPage<UserForum> discussPage =    forumService.getForums(userForumQueryParams);
        return ResponseEntity.status(HttpStatus.OK).body(discussPage);
    }

}
