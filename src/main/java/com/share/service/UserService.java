package com.share.service;

import com.share.pojo.User;

import javax.servlet.http.Cookie;

/**
 * Created by weixin on 17-7-31.
 */
public interface UserService {
//     User getUserById(User user);

     User login(User user);

     int SignUp(User user);

     int UpdateInfo(Cookie[] cookies,String uPwd,String uName);

     User CheckUname(String uName);

     double reCharge(User user);

     int loginChangeCon(User user);

     int Deduct(User user);

     User getName(User user);
}
