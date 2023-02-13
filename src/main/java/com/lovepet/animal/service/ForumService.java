package com.lovepet.animal.service;

import com.lovepet.animal.dto.ForumData;
import com.lovepet.animal.dto.UserForumQueryParams;
import com.lovepet.animal.model.UserForum;
import com.lovepet.animal.util.DiscussPage;

import java.util.List;

public interface ForumService {
    void createForum(ForumData forumData);
    DiscussPage<UserForum> getForums(UserForumQueryParams userForumQueryParams);
}
