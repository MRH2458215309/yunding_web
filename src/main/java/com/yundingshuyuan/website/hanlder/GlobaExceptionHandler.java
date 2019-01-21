/**
 * FileName:GlobaExceptionHandler
 * Author:leeyf
 * Date:19-1-20 下午3:01
 * Description:全局异常处理
 */
package com.yundingshuyuan.website.hanlder;

import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import com.yundingshuyuan.website.exception.SysException;
import com.yundingshuyuan.website.wrapper.ResultWrapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobaExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultWrapper exception(Exception e){
        e.printStackTrace();
        /**
         * 如果异常为系统异常,则按wrapper格式返回
         */
        if(e instanceof SysException){
            return ResultWrapper.failure(((SysException)e).getCode(),e.getMessage());
        }
        /**
         * 其他,则返回未知异常
         */
        return ResultWrapper.failure(ErrorCodeEnum.UK_KNOW_ERROR);
    }
}
