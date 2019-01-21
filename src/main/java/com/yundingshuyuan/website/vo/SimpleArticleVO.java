package com.yundingshuyuan.website.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SimpleArticleVO {

    /**
     * 注释标记
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面配图
     */
    @JsonProperty(value = "img")
    private String image;


    /**
     * 浏览量
     */
    private Integer browseNumber;

    /**
     * 点赞数
     */
    private Integer likeNumber;

    /**
     * 评论数
     */
    private Integer commentNumber;

    /**
     * 标签
     */
    private Integer label;

    /**
     * 创建时间
     */
    private Date createdAt;


}
