/**
 * FileName:UserIdentityEnum
 * Author:leeyf
 * Date:19-1-22 下午2:12
 * Description:用户身份枚举
 */
package com.yundingshuyuan.website.enums;

import lombok.Getter;

@Getter
public enum  UserIdentityEnum {
    /**
     *身份
     */
    IDENTITY_1(1,"学员"),
    IDENTITY_2(2,"工程师"),
    IDENTITY_3(3,"极客"),
    IDENTITY_4(4,"创客"),
    IDENTITY_5(5,"云顶院")
    ;

    UserIdentityEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 身份序号
     */
    private Integer status;
    /**
     * 描述
     */
    private String desc;


}
