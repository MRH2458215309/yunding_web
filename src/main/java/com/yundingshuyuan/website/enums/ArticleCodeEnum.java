package com.yundingshuyuan.website.enums;

import lombok.Getter;

@Getter
public enum ArticleCodeEnum {


    /**
     * 文章上传不能为空
     */
    ARTICLE_NONE(999,"上传文章信息不能为空");

    Integer code;

    String message;

    ArticleCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
