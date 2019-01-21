/**
 * FileName:UserLoginForm
 * Author:leeyf
 * Date:19-1-20 下午7:21
 * Description:用户登录表单
 */
package com.yundingshuyuan.website.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserLoginForm {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty
    @Size(min = 8,max = 16,message = "密码长度8-16")
    private String password;
}
