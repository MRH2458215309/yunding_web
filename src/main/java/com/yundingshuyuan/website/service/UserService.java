/**
 * FileName:UserService
 * Author:leeyf
 * Date:19-1-20 上午11:18
 * Description:
 */
package com.yundingshuyuan.website.service;

import com.yundingshuyuan.website.form.UserLoginByPhoneForm;
import com.yundingshuyuan.website.form.UserLoginForm;
import com.yundingshuyuan.website.form.UserPasswordForm;
import com.yundingshuyuan.website.form.UserRegisterForm;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 用户注册
     * @param userRegisterForm
     */
    void registerUser(UserRegisterForm userRegisterForm);

    /**
     * 用户认证
     * @param userLoginForm
     * @return
     */
    String auth(UserLoginForm userLoginForm);

    /**
     * 用户认证byPhone
     * @param userLoginByPhoneForm
     * @return
     */
    String auth(UserLoginByPhoneForm userLoginByPhoneForm);

    /**
     * 用户验证,成功则发送手机验证码
     * @param username
     */
    void checkUsername(String username);

    /**
     * 密码修改
     * @param passwordForm
     */
    void updatePassword(UserPasswordForm passwordForm);
}
