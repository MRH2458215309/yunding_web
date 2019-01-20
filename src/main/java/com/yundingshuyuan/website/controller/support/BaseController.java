/**
 * FileName:BaseController
 * Author:leeyf
 * Date:19-1-20 上午9:19
 * Description:
 */
package com.yundingshuyuan.website.controller.support;

import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import com.yundingshuyuan.website.exception.SysException;
import org.springframework.validation.BindingResult;

public class BaseController {
    protected void validateParams(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SysException(
                    ErrorCodeEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage()
            );
        }
    }
}
