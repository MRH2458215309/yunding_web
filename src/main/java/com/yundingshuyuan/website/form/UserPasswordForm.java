/**
 * FileName:UserPasswordForm
 * Author:leeyf
 * Date:19-1-21 下午1:52
 * Description:修改密码表单
 */
package com.yundingshuyuan.website.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserPasswordForm {
    @NotEmpty(message = "账号不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 8,max = 16,message = "密码长度8-16")
    private String password;

    @NotEmpty(message = "手机验证码不能为空")
    private String PhoneCode;
}
