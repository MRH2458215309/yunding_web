/**
 * FileName:ResultWrapper
 * Author:leeyf
 * Date:19-1-20 下午12:38
 * Description:结果wrapper
 */
package com.yundingshuyuan.website.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class ResultWrapper<T> {
    private final static Integer SUCCESS_CODE = 200;

    private final static String SUCCESS_MESSAGE = "操作成功";

    private final static Integer FAILURE_CODE = 500;

    private final static String FAILURE_MESSAGE = "操作失败";

    private Integer code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResultWrapper(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultWrapper(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static<T> ResultWrapper<T> success(){
        return new ResultWrapper<>(SUCCESS_CODE,SUCCESS_MESSAGE);
    }
    public static<T> ResultWrapper<T> success(String message){
        return new ResultWrapper<>(SUCCESS_CODE,message);
    }

    public static<T> ResultWrapper<T> successWithData(T data){
        return new ResultWrapper<>(SUCCESS_CODE,SUCCESS_MESSAGE,data);
    }

    public static<T> ResultWrapper<T> failure(){
        return new ResultWrapper<>(FAILURE_CODE,FAILURE_MESSAGE);
    }

    public static<T> ResultWrapper<T> failure(String message){
        return new ResultWrapper<>(FAILURE_CODE,message);
    }

    public static<T> ResultWrapper<T> failure(Integer code,String message){
        return new ResultWrapper<>(code,message);
    }

    public static<T> ResultWrapper<T> failure(ErrorCodeEnum errorCodeEnum){
        return new ResultWrapper<>(errorCodeEnum.getCode(),errorCodeEnum.getMessage());
    }
}
