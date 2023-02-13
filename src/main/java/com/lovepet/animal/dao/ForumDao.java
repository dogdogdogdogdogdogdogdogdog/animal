package com.lovepet.animal.dao;

import com.lovepet.animal.dto.ForumData;
import com.lovepet.animal.dto.UserForumQueryParams;
import com.lovepet.animal.model.UserForum;
import com.lovepet.animal.util.DiscussPage;

import java.util.List;

public interface ForumDao {

    void createForum(ForumData forumData);
    DiscussPage<UserForum> getForums(UserForumQueryParams userForumQueryParams);
}
