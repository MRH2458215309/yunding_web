/**
 * FileName:UserInterceptor
 * Author:leeyf
 * Date:19-1-20 下午6:55
 * Description:用户模块拦截器
 */
package com.yundingshuyuan.website.interceptor;

import com.yundingshuyuan.website.constant.SysConstant;
import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import com.yundingshuyuan.website.repository.redis.IRedisRepository;
import com.yundingshuyuan.website.utils.JsonUtils;
import com.yundingshuyuan.website.wrapper.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private IRedisRepository redisRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 获取access_token
         */
        String accessToken = request.getHeader(SysConstant.HEADER_TOKEN);
        //未获取到token
        if (null == accessToken) {
            accessToken = request.getParameter(SysConstant.TOKEN_REQUEST_PARAM);
        }
        response.setHeader(SysConstant.HTTP_HEADER_CONTENT_TYPE, SysConstant.CONTENT_TYPE_APPLICATION_JSON);

        ResultWrapper resultWrapper = null;
        if (null == accessToken) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resultWrapper = ResultWrapper.failure(ErrorCodeEnum.ERROR_TOKEN);
            response.getWriter().println(JsonUtils.toJson(resultWrapper));
            return false;
        }
        String userId = redisRepository.findUserIdByAccessToken(accessToken);
        if (null == userId) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resultWrapper = ResultWrapper.failure(ErrorCodeEnum.ERROR_TOKEN);
            response.getWriter().println(JsonUtils.toJson(resultWrapper));
            return false;
        }
        request.setAttribute("UserID",userId);
        return true;
    }
}
