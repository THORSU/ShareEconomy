package com.share.controller;

import com.share.pojo.Object_1;
import com.share.pojo.User;
import com.share.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixin on 17-8-20.
 */
@Controller
@RequestMapping("/cd")
public class logoutController {
    private Logger logger = Logger.getLogger(logoutController.class);
    @Autowired
    private UserService userService;
    private User user;
    @RequestMapping(value = "/logout.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    int logout(HttpServletRequest request, HttpServletResponse response) {

        final Cookie[] oCookies = request.getCookies();
        if (oCookies != null) {
            for (final Cookie oItem : oCookies) {
                final String sName = oItem.getName();
                if (sName.equals("ssid")) {
                    logger.info(oItem.getValue());
                    user=new User();
                    user.setUid(oItem.getValue());
                    User res=userService.getUserById(user);
                    res.setCondition("0");
                 int rescount=  userService.loginChangeCon(res);
                }
            }
        }

        return 1;
    }
}
