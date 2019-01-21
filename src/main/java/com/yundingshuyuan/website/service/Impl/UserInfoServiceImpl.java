/**
 * FileName:UserInfoServiceImpl
 * Author:leeyf
 * Date:19-1-21 下午2:50
 * Description:
 */
package com.yundingshuyuan.website.service.Impl;

import com.yundingshuyuan.website.entity.UserInfo;
import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import com.yundingshuyuan.website.exception.UserException;
import com.yundingshuyuan.website.form.InfoAddForm;
import com.yundingshuyuan.website.repository.UserInfoRepository;
import com.yundingshuyuan.website.service.UserInfoService;
import org.apache.coyote.Request;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author leeyf
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public void saveUserInfo(HttpServletRequest request,InfoAddForm infoAddForm) {
        //获得当前userId
        String userId= (String) request.getAttribute("UserID");
        UserInfo userInfo =new UserInfo();

        BeanUtils.copyProperties(userInfo,infoAddForm);
        userInfo.setId(userId);
        System.out.println(infoAddForm.toString());
        System.out.println(userInfo.toString());


    }
}
