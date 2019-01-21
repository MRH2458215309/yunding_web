/**
 * FileName:RedisRepositoryImpl
 * Author:leeyf
 * Date:19-1-20 下午7:06
 * Description:redis仓库实体类
 */
package com.yundingshuyuan.website.repository.redis.Impl;

import com.yundingshuyuan.website.repository.redis.IRedisRepository;
import org.springframework.stereotype.Component;

@Component
public class RedisRepositoryImpl implements IRedisRepository {
    @Override
    public String findAccessTokenByUserId(String userId) {
        return null;
    }

    @Override
    public void deleteAccessToken(String accessToken) {

    }

    @Override
    public void saveUserAccessToken(String userId, String accessToken) {

    }

    @Override
    public void saveAccessToken(String userId, String accessToken) {

    }

    @Override
    public String findUserByAccessToken(String accessToken) {
        return null;
    }
}
