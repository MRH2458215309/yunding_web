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
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 入学年份
     */
    private String series;

    /**
     * 方向
     */
    private String direction;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 学院
     */
    private String academy;

    /**
     * 专业
     */
    private String major;

    /**
     * 楼号
     */
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

