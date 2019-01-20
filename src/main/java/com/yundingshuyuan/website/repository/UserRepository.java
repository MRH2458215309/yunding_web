/**
 * FileName:UserRepository
 * Author:leeyf
 * Date:19-1-20 上午11:06
 * Description:dao层
 */
package com.yundingshuyuan.website.repository;

import com.yundingshuyuan.website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

}
