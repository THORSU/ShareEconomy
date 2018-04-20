package com.share.controller.PayController;

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
 * Created by weixin on 17-8-16.
 */
@Controller
@RequestMapping("/ef")
public class RechargeController {
    private static Logger logger = Logger.getLogger(RechargeController.class);
    @Autowired
    private UserService userService;
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
                    user.setWallet(Double.parseDouble(oItem1.getValue()) +Double.parseDouble(account));
                    double res = userService.reCharge(user);
                    if (res == 1) {
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
}
