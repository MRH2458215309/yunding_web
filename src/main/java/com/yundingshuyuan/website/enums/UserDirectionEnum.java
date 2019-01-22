package com.yundingshuyuan.website.enums;

import lombok.Getter;

/**
 * @author leeyf
 */
@Getter
public enum UserDirectionEnum {
    /*方向 01设计 02秘书处 03前端 04Java 05Python 06Node.js 07云顶机电团队*/

    DIRECTION_ENUM1(1,"设计"),
    DIRECTION_ENUM2(2,"秘书处"),
    DIRECTION_ENUM3(3,"前端"),
    DIRECTION_ENUM4(4,"JAVA"),
    DIRECTION_ENUM5(5,"Python"),
    DIRECTION_ENUM6(6,"Node.js"),
    DIRECTION_ENUM7(7,"云顶机电")
    ;

    UserDirectionEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    private Integer status;

    private String desc;
}
