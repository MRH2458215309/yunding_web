/**
 * FileName:UserServiceImpl
 * Author:leeyf
 * Date:19-1-20 上午11:19
 * Description:
 */
package com.yundingshuyuan.website.service.Impl;

import com.yundingshuyuan.website.entity.User;
import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import com.yundingshuyuan.website.enums.UserStateEnum;
import com.yundingshuyuan.website.exception.SysException;
import com.yundingshuyuan.website.exception.UserException;
import com.yundingshuyuan.website.form.UserRegisterForm;
import com.yundingshuyuan.website.repository.UserRepository;
import com.yundingshuyuan.website.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    //连接redis
    Jedis jedis = new Jedis("localhost");

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void registerUser(UserRegisterForm userRegisterForm) {

        /**
         * 第一步,验证图片验证
         */
        String verCode = userRegisterForm.getVerificationCode();
        /*从缓存获取图片验证码*/
        String ver_code = jedis.get("VER_CODE");
        if (ver_code != null) {
            if (!ver_code.equals(verCode)) {
                throw new UserException(ErrorCodeEnum.CODE_ERROR);
            }
        }else {
            throw new NullPointerException();
        }
        /**
         * 第二步,验证手机验证码
         */
        String phoneCode = userRegisterForm.getPhoneCode();
        /*从缓存获取*/
        String phone_code = jedis.get("PHONE_CODE");
        if (!phone_code.equals(phoneCode)) {
            throw new UserException(ErrorCodeEnum.PHONE_CODE_ERROR);
        }

        /**
         * 第三步,查询用户是否已被注册
         */
        String username = userRegisterForm.getUsername();
        User user = new User();
        user.setUsername(username);
        /*Optional是一个可以为空的容器,如果isPresent()为true则存在值,调用get()获取对象*/
        Optional<User> userOptional = userRepository.findOne(Example.of(user));

        if (userOptional.isPresent()) {
            /*如果用户存在,则抛出用户存在异常*/
            throw new UserException(ErrorCodeEnum.USERNAME_EXISTS);
        }
        /**
         *第四步,填充并且保存用户信息
         */
        BeanUtils.copyProperties(userRegisterForm, user);
        user.setStatus(UserStateEnum.NOMAL.getStatus());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        userRepository.save(user);

    }
}
