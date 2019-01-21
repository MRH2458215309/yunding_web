package com.yundingshuyuan.website.repository;

import com.yundingshuyuan.website.entity.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdentityRepository extends JpaRepository<UserIdentity,String> {
}
