/**
 * FileName:UserInfoMyselfVO
 * Author:leeyf
 * Date:19-1-22 上午11:42
 * Description:个人主页个人信息VO
 */
package com.yundingshuyuan.website.vo;

import lombok.Data;

@Data
public class UserInfoMyselfVO {
    /**
     * 姓名
     */
    private String realName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 学院
     */
    private String academy;
    /**
     * 身份
     */
    private String identity;
    /**
     * 云顶学习方向
     */
    private String direction;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 头像
      */
    private String image;
}
