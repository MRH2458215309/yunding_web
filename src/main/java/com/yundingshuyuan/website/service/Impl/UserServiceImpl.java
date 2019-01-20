/**
 * FileName:UserServiceImpl
 * Author:leeyf
 * Date:19-1-20 上午11:19
 * Description:
 */
package com.yundingshuyuan.website.service.Impl;

import com.yundingshuyuan.website.repository.UserRepository;
import com.yundingshuyuan.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

}
