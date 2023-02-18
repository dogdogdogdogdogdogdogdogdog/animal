package com.lovepet.animal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ForumArticleRequest {

    @NotNull
    private Integer userId;
    @NotNull
    private String type;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String content;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
