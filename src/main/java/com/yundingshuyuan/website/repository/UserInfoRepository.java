package com.yundingshuyuan.website.repository;

import com.yundingshuyuan.website.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

}
