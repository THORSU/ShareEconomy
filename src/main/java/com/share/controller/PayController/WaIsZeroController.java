package com.share.controller.PayController;

import com.share.pojo.User;
import com.share.service.UserForRedisService;
import com.share.service.UserService;
import org.apache.log4j.Logger;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by weixin on 17-9-9.
 */
@Controller
@RequestMapping("/ef")
public class WaIsZeroController {
    private static final Logger logger = Logger.getLogger(WaIsZeroController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserForRedisService userForRedisService;
    private String userName;

    @RequestMapping(value = "/IsZero.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    Object IsZero(HttpServletRequest request,HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                final String sname = cookie.getName();
                if (sname.equals("ssname")) {
                   userName = cookie.getValue();
                    logger.info(cookie.getValue());
                }
            }
            User user = userForRedisService.findUserInfo(userName);
            if (Objects.isNull(user)){
                User res1 = userService.CheckUname(userName);
                if (res1.getWallet()> 0) {
                    logger.info(res1+"000");
                    return res1.getWallet();
                } else {
                    logger.error(res1);
                    return "0";
                }
            }else if (user.getWallet()>0){
                logger.info(user+"redis检查余额不为0");
                return user.getWallet();
            }
            else {
                logger.error("redis检查余额为0");
                return "0";
            }
        }
        else {return null;}
    }
}
