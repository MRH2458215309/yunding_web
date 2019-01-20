/**
 * FileName:UserStateEnum
 * Author:leeyf
 * Date:19-1-20 上午9:30
 * Description:用户状态枚举
 */
package com.yundingshuyuan.website.enums;

import lombok.Getter;

@Getter
public enum UserStateEnum {
    NOMAL(0,"正常"),

    DISABLED(-1,"封号")
;
    UserStateEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    private Integer status;

    private String desc;
}
