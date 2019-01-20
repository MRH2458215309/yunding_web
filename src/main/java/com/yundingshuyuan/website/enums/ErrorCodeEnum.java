/**
 * FileName:ErrorCodeEnum
 * Author:leeyf
 * Date:19-1-20 上午9:30
 * Description:
 */
package com.yundingshuyuan.website.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    /**
     * 未知错误
     */
    UK_KNOW_ERROR(999,"系统繁忙,请稍后再试"),

    /**
     * 用户名已经被注册
     */
    USERNAME_EXISTS(1001,"用户名已经存在"),

    /**
     * 用户名错误
     */
    USERNAME_ERROR(1001,"用户名错误"),

    /**
     *参数错误
     */
    PARAM_ERROR(1001,"参数错误"),

    /**
     * 验证码错误
     */
    CODE_ERROR(1002,"图片验证码错误"),

    /**
     *手机验证码错误
     */
    PHONE_CODE_ERROR(1002,"手机验证码错误");
    ErrorCodeEnum(Integer code,String message){
        this.code =code;
        this.message = message;
    }

    Integer code;

    String message;


}
