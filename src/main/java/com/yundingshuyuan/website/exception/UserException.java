/**
 * FileName:UserException
 * Author:leeyf
 * Date:19-1-20 上午9:28
 * Description:用户异常
 */
package com.yundingshuyuan.website.exception;

import com.yundingshuyuan.website.enums.ErrorCodeEnum;

public class UserException extends SysException{

    public UserException(Integer code,String message){
        super(code,message);
    }

    public UserException(){

    }

    public UserException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum);
    }

}
