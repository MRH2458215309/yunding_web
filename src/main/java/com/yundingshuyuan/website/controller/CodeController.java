/**
 * FileName:CodeController
 * Author:leeyf
 * Date:19-1-14 下午4:00
 * Description:
 */
package com.yundingshuyuan.website.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import com.yundingshuyuan.website.exception.SysException;
import com.yundingshuyuan.website.utils.VerificationCode.*;
import com.yundingshuyuan.website.utils.aliyunCode.CodeUtil;
import com.yundingshuyuan.website.wrapper.ResultWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.yundingshuyuan.website.utils.aliyunCode.Code.sendSms;
@Api(tags = "验证码接口")
@RestController
@RequestMapping(value = "/code")
@Slf4j
public class CodeController {
    //连接本地的 Redis 服务
    Jedis jedis = new Jedis("localhost");
    /**
     * 获取图片验证码（Gif版本）
     * @param response
     */
    @ApiOperation("获取图片验证码")
    @RequestMapping(value="getGifCode",method= RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146,33,4);
            //输出
            captcha.out(response.getOutputStream());
            //HttpSession session = request.getSession(true);
            //存入Session
            //session.setAttribute("_code",captcha.text().toLowerCase());
            //将验证码保存到缓存
            jedis.set("VER_CODE",captcha.text().toLowerCase());
            //设置缓存过期时间,5分钟后失效
            jedis.expire("VER_CODE",300);
        } catch (Exception e) {
            throw new SysException(ErrorCodeEnum.CODE_ERROR);
        }
        System.out.println("图片验证码请求成功"+jedis.get("VER_CODE"));
    }

    /**
     * 手机验证提交
     */
    @ApiOperation("获取手机验证码")
    @RequestMapping(value = "/getPhoneCode", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper code(String username) {
        //先判断手机号是否正确
        boolean isphone = CodeUtil.isPhone(username);
        if (!isphone) {
            return ResultWrapper.failure("手机号格式错误");
        } else {
            //生成验证码
            String smsCode = CodeUtil.smsCode();
            String getreturn;
            try {
                SendSmsResponse response = sendSms(username, smsCode);
                //将手机验证码存入缓存
                jedis.set("PHONE_CODE",smsCode);
                //设置缓存过期时间,5分钟后失效
                jedis.expire("PHONE_CODE",300);
                //后台打印手机号
                System.out.println("手机号:"+username);
                System.out.println("手机验证码:"+smsCode);
                getreturn = "Code:" + response.getCode() + "\nMessage:" + response.getMessage();
                System.out.println(getreturn);
            } catch (ClientException e) {
                e.printStackTrace();
            }
            return ResultWrapper.success("短信验证发送成功");
        }
    }

    @RequestMapping(value = "/getCode",method = RequestMethod.GET)
    public String getCode(HttpServletRequest request){
        //System.out.println((String) request.getSession().getAttribute("_code"));
        Jedis jedis =new Jedis("localhost");
        String code= jedis.get("VER_CODE");
        System.out.println(code);
        return code;
    }
}
