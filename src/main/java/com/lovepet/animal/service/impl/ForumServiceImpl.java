package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.ForumDao;
import com.lovepet.animal.dto.ForumData;
import com.lovepet.animal.dto.UserForumQueryParams;
import com.lovepet.animal.model.UserForum;
import com.lovepet.animal.service.ForumService;
import com.lovepet.animal.util.DiscussPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForumServiceImpl implements ForumService {

    @Autowired
    ForumDao forumDao;

    @Override
    public void createForum(ForumData forumData) {
        forumDao.createForum(forumData);;
    }

    @Override
    public DiscussPage<UserForum> getForums(UserForumQueryParams userForumQueryParams) {
        return forumDao.getForums(userForumQueryParams);
    }


}
