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
    /**
     * 正常
     */
    NOMAL(0,"正常"),
    /**
     * 封号
     */
    DISABLED(-1,"封号")

;
    UserStateEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 账号状态
     */
    private Integer status;
    /**
     * 描述
     */
    private String desc;
}
