package com.share.controller.PayController;

import com.share.pojo.User;
import com.share.service.UserForRedisService;
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
import java.util.Objects;

/**
 * Created by weixin on 17-8-16.
 */
@Controller
@RequestMapping("/ef")
public class RechargeController {
    private static Logger logger = Logger.getLogger(RechargeController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserForRedisService userForRedisService;
    private User user;

    @RequestMapping(value = "/recharge.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object GetAccount(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("account").trim();//update wallet depend on sname;
        final Cookie[] oCookies = request.getCookies();
        if (oCookies != null) {
            for (final Cookie oItem : oCookies) {
                final String sName = oItem.getName();
                if (sName.equals("ssname")) {
                    user = new User();
                    user.setUname(oItem.getValue());
                }
            }
            for(final Cookie oItem1:oCookies) {//recharge
                final String sAccount = oItem1.getName();
                if (sAccount.equals("ssaccount")) {
                    Double wallet = Double.parseDouble(oItem1.getValue()) +Double.parseDouble(account);
                    user.setWallet(wallet);
                    oItem1.setValue(wallet.toString());
                    double res = userService.reCharge(user);
                    if (res == 1) {
                        User userInfo = userForRedisService.findUserInfo(user.getUname());
                        if (!Objects.isNull(userInfo)){
                            userInfo.setWallet(wallet);
                            userForRedisService.insertUserInfo(userInfo);
                        }
                        Cookie cookie = new Cookie("ssaccount",wallet.toString());
                        cookie.setPath("/");
                        cookie.setMaxAge(60*60*24);
                        response.addCookie(cookie);
                        logger.info(res);
                        return "1";
                    } else {
                        logger.error(res);
                        return "0";
                    }
                }
            }
            }
        else {
            return "0";
        }
        return "0";
    }


    @RequestMapping(value = "/confirmPwd.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object confirmPwd(HttpServletRequest request, HttpServletResponse response) {
        String pwd = request.getParameter("confirmPwd").trim();
        final Cookie[] oCookies = request.getCookies();
        if (oCookies != null) {
            for (final Cookie oItem : oCookies) {
                final String sName = oItem.getName();
                if (sName.equals("ssname")) {
                    User user = userService.CheckUname(oItem.getValue());
                    if (!Objects.isNull(user)){
                        if (pwd.equals(user.getUpwd().substring(0,6))){
                            return 1;
                        }
                    }
                }
            }
        }
        else {
            return "0";
        }
        return "0";
    }
}
