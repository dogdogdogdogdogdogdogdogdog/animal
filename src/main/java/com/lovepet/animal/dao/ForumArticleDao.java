package com.lovepet.animal.dao;

import com.lovepet.animal.dto.ForumArticleQueryParams;
import com.lovepet.animal.model.ForumArticle;

import java.util.List;

public interface ForumArticleDao {

    Integer countForumArticle(ForumArticleQueryParams forumArticleQueryParams);

    List<ForumArticle> getForumArticles(ForumArticleQueryParams forumArticleQueryParams);

    ForumArticle getForumArticleById(Integer forumArticleId);
}
