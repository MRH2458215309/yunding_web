/**
 * FileName:UserController
 * author:leeyf
 * Date:19-1-20 上午11:03
 * Description:用户控制
 */
package com.yundingshuyuan.website.controller;

import com.yundingshuyuan.website.controller.support.BaseController;
import com.yundingshuyuan.website.form.UserLoginByPhoneForm;
import com.yundingshuyuan.website.form.UserLoginForm;
import com.yundingshuyuan.website.form.UserPasswordForm;
import com.yundingshuyuan.website.form.UserRegisterForm;
import com.yundingshuyuan.website.service.UserService;
import com.yundingshuyuan.website.wrapper.ResultWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Api(tags = "用户接口")
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @ApiOperation("用户注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultWrapper register(@Valid/*校验表单*/ UserRegisterForm userRegisterForm,
                                  BindingResult bindingResult){
        /*对表单验证或者赋值*/
        validateParams(bindingResult);

        userService.registerUser(userRegisterForm);

        return ResultWrapper.success();
    }

    @ApiOperation("用户登录(账号登录)")
    @PostMapping("/login")
    public ResultWrapper<String> login(UserLoginForm userLoginForm,BindingResult bindingResult){

        validateParams(bindingResult);

        String accessToken = userService.auth(userLoginForm);

        return ResultWrapper.success(accessToken);

    }

    @ApiOperation("用户登录(手机登录)")
    @PostMapping("/loginByPhone")
    public ResultWrapper<String> login(@Valid UserLoginByPhoneForm userLoginByPhoneForm, BindingResult bindingResult){
        validateParams(bindingResult);

        String accessToken = userService.auth(userLoginByPhoneForm);

        return ResultWrapper.success(accessToken);

    }
    @ApiOperation("忘记密码_验证")
    @GetMapping("/checkUsername")
    public ResultWrapper checkUsername(String username){

        userService.checkUsername(username);

        return ResultWrapper.success();
    }

    @ApiOperation("忘记密码_修改")
    @PostMapping("/updatePassword")
    public ResultWrapper updatePassword(UserPasswordForm passwordForm,BindingResult bindingResult){
        validateParams(bindingResult);

        userService.updatePassword(passwordForm);

        return ResultWrapper.success("修改密码操作成功");
    }

}
