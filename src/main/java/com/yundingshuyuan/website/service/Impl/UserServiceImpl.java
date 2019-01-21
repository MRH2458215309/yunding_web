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
import com.yundingshuyuan.website.form.UserLoginByPhoneForm;
import com.yundingshuyuan.website.form.UserLoginForm;
import com.yundingshuyuan.website.form.UserRegisterForm;
import com.yundingshuyuan.website.repository.UserRepository;
import com.yundingshuyuan.website.repository.redis.IRedisRepository;
import com.yundingshuyuan.website.service.UserService;
import com.yundingshuyuan.website.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

/**
 * @author leeyf
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private IRedisRepository redisRepository;
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
        //获取图像验证码成功
        if (ver_code != null) {
            if (!ver_code.equals(verCode)) {
                throw new UserException(ErrorCodeEnum.CODE_ERROR);
            }
        } else {
            throw new SysException(ErrorCodeEnum.PARAM_ERROR);
        }
        /**
         * 第二步,验证手机验证码
         */
        String phoneCode = userRegisterForm.getPhoneCode();
        /*从缓存获取*/
        String phone_code = jedis.get("PHONE_CODE");
        //获取手机验证码成功
        if (phone_code != null) {
            if (!phone_code.equals(phoneCode)) {
                throw new UserException(ErrorCodeEnum.PHONE_CODE_ERROR);
            }
        }else {
            throw new SysException(ErrorCodeEnum.PARAM_ERROR);
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


    @Override
    public String auth(UserLoginForm userLoginForm) {
        /**
         * 第一步,查询用户信息
         */
        User user = new User();
        user.setUsername(userLoginForm.getUsername());
        Optional<User> userOptional = userRepository.findOne(Example.of(user));

        //用户名错误
        if(!userOptional.isPresent()){
            throw new UserException(ErrorCodeEnum.USERNAME_ERROR);
        }
        user = userOptional.get();

        /**
         * 第二步,判断密码是否正确
         */
        //密码错误
        if(!user.getPassword().equals(userLoginForm.getPassword())){
            throw new UserException(ErrorCodeEnum.PASSWORD_ERROR);
        }

        /**
         * 第三步,生成accessToken
         */
        String accessToken = TokenUtils.genToken();

        /**
         * 第四步,获取旧token
         */
        //获取userId
        String userId = user.getId();
        String userAccessTokenKey = "USER_TOKEN:"+ userId;

        String oldToken = redisRepository.findAccessTokenByUserId(userId);

        if(oldToken!=null){
            //TODO 消息提醒当前账号异地登录
            //删除旧token
            redisRepository.deleteAccessToken(oldToken);
        }
        //保存id与token的关系
        redisRepository.saveUserAccessToken(userId,accessToken);
        //保存token
        redisRepository.saveAccessToken(userId,accessToken);
        return accessToken;
    }

    @Override
    public String auth(UserLoginByPhoneForm userLoginByPhoneForm) {
        /**
         * 第一步,查询用户信息
         */
        User user = new User();
        user.setUsername(userLoginByPhoneForm.getUsername());
        Optional<User> userOptional = userRepository.findOne(Example.of(user));

        //用户名错误
        if(!userOptional.isPresent()){
            throw new UserException(ErrorCodeEnum.USERNAME_ERROR);
        }
        user = userOptional.get();

        /**
         * 第二步,判断验证码是否正确
         */
        return null;

    }
}
