/**
 * FileName:UserController
 * Author:leeyf
 * Date:19-1-20 上午11:03
 * Description:用户控制
 */
package com.yundingshuyuan.website.controller;

import com.yundingshuyuan.website.controller.support.BaseController;
import com.yundingshuyuan.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController extends BaseController {
    @Autowired
    UserService userService;

}
