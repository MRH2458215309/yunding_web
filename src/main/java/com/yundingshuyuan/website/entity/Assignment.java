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
@Entity(name = "t_assignment")
public class Assignment {

    /**
     * 注释标记
     */
    @Id
    @GeneratedValue()
    private Integer id;

    /**
     * 习作名称
     */
    private String name;

    /**
     * 习作描述
     */
    private String introduce;

    /**
     * 配图
     */
    private String image;

    /**
     * 详情
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
