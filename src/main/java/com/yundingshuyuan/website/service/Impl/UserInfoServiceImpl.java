/**
 * FileName:UserInfoServiceImpl
 * Author:leeyf
 * Date:19-1-21 下午2:50
 * Description:
 */
package com.yundingshuyuan.website.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.yundingshuyuan.website.constant.UserConstant;
import com.yundingshuyuan.website.entity.UserIdentity;
import com.yundingshuyuan.website.entity.UserInfo;
import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import com.yundingshuyuan.website.enums.UserDirectionEnum;
import com.yundingshuyuan.website.enums.UserIdentityEnum;
import com.yundingshuyuan.website.exception.UserException;
import com.yundingshuyuan.website.form.InfoAddForm;
import com.yundingshuyuan.website.repository.UserIdentityRepository;
import com.yundingshuyuan.website.repository.UserInfoRepository;
import com.yundingshuyuan.website.service.UserInfoService;

import com.yundingshuyuan.website.vo.UserInfoMyselfVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

/**
 * @author leeyf
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserIdentityRepository userIdentityRepository;

    @Override
    public void saveUserInfo(HttpServletRequest request, InfoAddForm infoAddForm, String imagePath) {
        //获得当前userId
        String userId = (String) request.getAttribute("UserID");
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        /**
         * 从数据库查询已知信息
         */
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userId);
        if(userInfoOptional.isPresent()) {
            userInfo = userInfoOptional.get();
        }
        System.out.println(userInfo.toString()+"-------------1");
        //hutool工具类  只将不为空的值赋值
        BeanUtil.copyProperties(infoAddForm,userInfo,
                CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        /*UserInfo(id=ff808181686a77c701686a77f07f0000, realName=李亚飞, sex=1, series=2017, direction=03, birthday=null, academy=软件学院, major=软件工程, classroom=1711, nativePlace=晋中, dormitory=null, room=null, image=null, signature=222222222222, createdAt=null, updatedAt=null)*/
        System.out.println(userInfo.toString()+"--------------2");
        //如果创建时间为空,则添加创建时间
        if (null == userInfo.getCreatedAt()) {
            userInfo.setCreatedAt(new Date());
        }
        userInfo.setUpdatedAt(new Date());
        //如果图片为空,则使用默认图片
        if (null == imagePath) {
            userInfo.setImage(UserConstant.IMAGE_URL);
        } else {
            userInfo.setImage(imagePath);
        }

        userInfoRepository.save(userInfo);


    }

    @Override
    public UserInfoMyselfVO findInfo(HttpServletRequest request) {
        /**
         * 当前登录账号id获取
         */
        String userID = (String) request.getAttribute("UserID");
        /**
         * 根据id获取信息
         */
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userID);
        if(!userInfoOptional.isPresent()){
            throw new UserException(ErrorCodeEnum.USERINFO_ERROR);
        }
        UserInfoMyselfVO userInfoMyselfVO =new UserInfoMyselfVO();
        BeanUtil.copyProperties(userInfoOptional.get(), userInfoMyselfVO);
        /**
         * 根据id获取身份
         */
        Optional<UserIdentity> userIdentityOptional = userIdentityRepository.findById(userID);
        if(!userIdentityOptional.isPresent()){
            throw new UserException(ErrorCodeEnum.IDENTITY_ERROR);
        }
        /*将身份status转化成对应文字*/
        userInfoMyselfVO.setIdentity(identity(userIdentityOptional.get().getDetail()));
        /*将方向status转化成对应文字*/
        userInfoMyselfVO.setDirection(direction(userInfoOptional.get().getDirection()));
        return userInfoMyselfVO;
    }

        String identity(Integer integer){
        /*身份：1：学员；2：工程师；3：极客；4：创客；5：云顶院；*/
            return UserIdentityEnum.valueOf("IDENTITY_"+integer).getDesc();
        }
        String direction(Integer integer){
        /*方向 01设计 02秘书处 03前端 04Java 05Python 06Node.js 07云顶机电团队*/
            return UserDirectionEnum.valueOf("DIRECTION_ENUM"+integer).getDesc();
        }
}
