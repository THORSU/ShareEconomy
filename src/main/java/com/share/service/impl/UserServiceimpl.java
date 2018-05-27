package com.share.service.impl;

import com.share.mapper.UserMapper;
import com.share.pojo.User;
import com.share.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

/**
 * Created by weixin on 17-7-31.
 */
@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(User user) {
        return userMapper.getUserById(user);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public int SignUp(User user) {
        try {
            User resultUser = userMapper.CheckUname(user.getUname());
            if (resultUser!=null){
                return 2;
            }else {
                return userMapper.SignUp(user);
            }
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int UpdateInfo(Cookie[] oCookies, String uPwd, String uName) {
        try {
            User user = new User();
            if (oCookies != null) {
                for (final Cookie oItem : oCookies) {
                    final String sName = oItem.getName();
                    if (sName.equals("ssid")) {

                        user.setUid(oItem.getValue());
                        if (StringUtils.isNotEmpty(uPwd)) {
                            user.setUpwd(uPwd);
                        }
                        if (StringUtils.isNotEmpty(uName)) {
                            user.setAlias(uName);
                        }
                    }
                }
                return userMapper.UpdateInfo(user);
            }
            else return 0;
        }catch (Exception e){
            return 0;
        }

    }


    @Override
    public User CheckUname(String uName) {
        return userMapper.CheckUname(uName);
    }

    @Override
    public double reCharge(User user) {
        return userMapper.reCharge(user);
    }

    @Override
    public int loginChangeCon(User user) {
        return userMapper.loginChangeCon(user);
    }

    @Override
    public int Deduct(User user) {
        return userMapper.Deduct(user);
    }

//    @Override
//    public double IsZero(User user) {
//        return  userMapper.IsZero(user);
//    }

    @Override
    public User getName(User user) {
        return userMapper.getName(user);
    }

}




