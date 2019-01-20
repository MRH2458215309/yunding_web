/**
 * FileName:UserService
 * Author:leeyf
 * Date:19-1-20 上午11:18
 * Description:
 */
package com.yundingshuyuan.website.service;

import com.yundingshuyuan.website.form.UserRegisterForm;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    void registerUser(UserRegisterForm userRegisterForm);
}
