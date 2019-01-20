/**
 * FileName:UserController
 * Author:leeyf
 * Date:19-1-20 上午11:03
 * Description:用户控制
 */
package com.yundingshuyuan.website.controller;

import com.yundingshuyuan.website.controller.support.BaseController;
import com.yundingshuyuan.website.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户接口")
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController extends BaseController {
    @Autowired
    UserService userService;


}
