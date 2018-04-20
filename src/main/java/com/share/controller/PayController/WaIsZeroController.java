package com.share.controller.PayController;

import com.share.pojo.User;
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

/**
 * Created by weixin on 17-9-9.
 */
@Controller
@RequestMapping("/ef")
public class WaIsZeroController {
    private static final Logger logger = Logger.getLogger(WaIsZeroController.class);
    @Autowired
    private UserService userService;
    private User user;

    @RequestMapping(value = "/IsZero.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    Object IsZero(HttpServletRequest request,HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                final String sname = cookie.getName();
                if (sname.equals("ssname")) {
                    user = new User();
//                    String xxx=cookie.getValue();
//                    System.out.println(cookie.getValue());
                    user.setUname(cookie.getValue());
                    logger.info(cookie.getValue());
                }
            }
            User res1 = userService.CheckUname(user);
            if (res1.getWallet()> 0) {
                logger.info(res1+"000");
                return res1.getWallet();
            } else {
                logger.error(res1);
                return "0";
            }
        }
        else {return null;}
    }
}
