package com.yundingshuyuan.website.service;

import com.yundingshuyuan.website.form.InfoAddForm;
import com.yundingshuyuan.website.vo.UserInfoMyselfVO;

import javax.servlet.http.HttpServletRequest;

public interface UserInfoService {
    /**
     * 保存用户信息
     * @param infoAddForm
     */
    void saveUserInfo(HttpServletRequest request,InfoAddForm infoAddForm,String imagePath);

    UserInfoMyselfVO findInfo(HttpServletRequest request);
}
