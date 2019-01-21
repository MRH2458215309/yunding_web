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
    USERNAME_ERROR(1002,"用户名错误"),

    /**
     *密码错误
     */
    PASSWORD_ERROR (1003,"密码错误"),

    /**
     *参数错误
     */
    PARAM_ERROR(1004,"参数错误"),

    /**
     * 验证码错误
     */
    CODE_ERROR(1005,"图片验证码错误"),

    /**
     *手机验证码错误
     */
    PHONE_CODE_ERROR(1006,"手机验证码错误"),

    /**
     *token异常
     */
    ERROR_TOKEN(1007,"token错误"),

    /**
     * JSON转化错误
     */
    JSON_TRANS_ERROR(1008,"JSON转化错误"),
    /**
     * 用户身份异常
     */
    IDENTITY_ERROR(1009,"User身份错误");



    ErrorCodeEnum(Integer code, String message){
        this.code =code;
        this.message = message;
    }

    Integer code;

    String message;


}
