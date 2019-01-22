/**
 * FileName:UserInfo
 * Author:leeyf
 * Date:19-1-21 上午11:01
 * Description:用户详细信息
 */
package com.yundingshuyuan.website.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity(name = "t_info")
public class UserInfo {
    /**
     * userID
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 入学年份
     */
    @Column(name = "series")
    private String series;

    /**
     * 方向
     */
    @Column(name = "direction")
    private String direction;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 学院
     */
    @Column(name = "academy")
    private String academy;

    /**
     * 专业
     */
    @Column(name = "major")
    private String major;

    /**
     * 班级
     */
    @Column(name = "class")
    private String classroom;

    /**
     * 籍贯
     */
    @Column(name = "native_place")
    private String nativePlace;

    /**
     * 楼号
     */
    @Column(name = "dormitory")
    private Integer dormitory;

    /**
     * 宿舍号
     */
    private Integer room;

    /**
     * 头像url
     */
    private String image;

    /**
     * 签名
     */
    @Column(name = "signature")
    private String signature;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}

