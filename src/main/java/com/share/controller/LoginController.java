package com.share.controller;


import com.share.pojo.User;

import com.share.service.UserForRedisService;
import com.share.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by weixin on 17-7-31.
 */
@Controller
@RequestMapping("/cd")//mapping
public class LoginController {
    private final static String CONDITION_TRUE = "0";
    private static Logger log=Logger.getLogger(LoginController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserForRedisService userForRedisService;

    private User user;
    @RequestMapping(value = "/login.form",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username").trim();//get the values from client.
        String password = request.getParameter("password").trim();
        user = new User();
        user.setUname(username);//set the values
        user.setUpwd(password);
        User userInfo = userForRedisService.findUserInfo(username);
        if (!Objects.isNull(userInfo)){
            if (password.equals(userInfo.getUpwd())){
                if (userInfo.getCondition().equals(CONDITION_TRUE)){
                    userInfo.setCondition("1");
                    userForRedisService.insertUserInfo(userInfo);
                    //save the cookie.
                    Cookie cookie1 = new Cookie("ssname", userInfo.getUname());
                    Cookie cookie2 = new Cookie("ssaccount", userInfo.getWallet() + "");
                    //the root directory
                    cookie1.setPath("/");
                    cookie2.setPath("/");

                    cookie1.setMaxAge(60 * 60 * 24);
                    cookie2.setMaxAge(60 * 60 * 24);

                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                    log.info(userInfo.toString()+"redis登陆成功");
                    return 1;
                }else {
                    log.info("redis该用户已经登录");
                    return "2";
                }
            }else {
                log.error("redis用户名或密码错误");
                return "0";}
        }else {
            User res = userService.login(user);
            if (res != null) {
                if (res.getCondition().equals(CONDITION_TRUE)) {//change the condition
                    res.setCondition("1");
                    int rescount = userService.loginChangeCon(res);
                    Cookie cookie = new Cookie("ssid", res.getUid());//save the cookie.
                    Cookie cookie1 = new Cookie("ssname", res.getUname());
                    Cookie cookie2 = new Cookie("ssaccount", res.getWallet() + "");
                    cookie.setPath("/");//the root directory
                    cookie1.setPath("/");
                    cookie2.setPath("/");
                    cookie.setMaxAge(60 * 60 * 24);
                    cookie1.setMaxAge(60 * 60 * 24);
                    cookie2.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                    log.info(res.toString()+"mysql登陆成功");
                    return "1";
                } else {
                    log.info("该用户已经登录");
                    return "2";}

            } else {
                log.error("用户名或密码错误");
                return "0";}
        }
        }
    }


