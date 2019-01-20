/**
 * FileName:UserRegisterForm
 * Author:leeyf
 * Date:19-1-20 下午12:00
 * Description:用户注册表单
 */
package com.yundingshuyuan.website.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegisterForm {
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 8,max = 16,message = "密码长度在8-16")
    private String password;

    @NotEmpty(message = "图像验证码不能为空")
    private String verificationCode;

    @NotEmpty(message = "手机验证码不能为空")
    private String phoneCode;
}
