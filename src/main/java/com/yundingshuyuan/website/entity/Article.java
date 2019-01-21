package com.yundingshuyuan.website.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Mr.h
 */
@Data
@Entity(name = "t_article")
public class Article {

    /**
     * 注释标记
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面配图
     */
    private String image;

    /**
     * 内容
     */
    private String content;

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

    /**
     * 更改时间
     */
    private Date updatedAt;

    public Article(){}

}
