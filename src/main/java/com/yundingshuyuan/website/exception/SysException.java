/**
 * FileName:SysException
 * Author:leeyf
 * Date:19-1-20 上午9:27
 * Description:系统异常
 */
package com.yundingshuyuan.website.exception;

import com.yundingshuyuan.website.enums.ArticleCodeEnum;
import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class SysException extends RuntimeException {
    private Integer code;

    public SysException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public SysException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
    }

    public SysException(){

    }

    public SysException(ArticleCodeEnum articleNone) {
        super(articleNone.getMessage());
        this.code = articleNone.getCode();
    }
}
