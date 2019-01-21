/**
 * FileName:UserLoginByPhoneForm
 * Author:leeyf
 * Date:19-1-21 上午9:41
 * Description:验证码登录表单
 */
package com.yundingshuyuan.website.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class UserLoginByPhoneForm {
    @NotEmpty
    private String username;

    @NotEmpty
    private String phoneCode;
}
