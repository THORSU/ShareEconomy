package com.share.controller;

import com.share.pojo.User;
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

/**
 * Created by weixin on 17-7-31.
 */
@Controller
@RequestMapping("/cd")//mapping
public class LoginController {
    private static Logger log=Logger.getLogger(LoginController.class);
    @Autowired
    private UserService userService;
    private User user;
    @RequestMapping(value = "/login.form",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username").trim();//get the values from client.
        String password = request.getParameter("password").trim();
        user = new User();
        user.setUname(username);//set the values
        user.setUpwd(password);
        User res = userService.login(user);

        if (res != null) {
            if (res.getCondition().equals("0")) {//change the condition
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
                    log.info(res.toString());
                    return "1";

                } else {return "2";}

            } else {return "0";}
        }
    }


