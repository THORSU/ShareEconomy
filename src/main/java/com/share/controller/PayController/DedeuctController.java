package com.share.controller.PayController;

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
 * Created by weixin on 17-9-7.
 */
@Controller
@RequestMapping("/ef")
public class DedeuctController {
    private static final Logger logger=Logger.getLogger(DedeuctController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserForRedisService userForRedisService;
    private User user;
    @RequestMapping(value = "/deduct.from",method = RequestMethod.POST,produces ="application/json;charset=utf-8")
    public @ResponseBody
    Object deduct(HttpServletRequest request,HttpServletResponse response){
        String bill=request.getParameter("bill").trim();
        final Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cookie:cookies) {
                final String sname=cookie.getName();
                if(sname.equals("ssname")){
                    user=new User();
                    user.setUname(cookie.getValue());
                }
            }

            for (final Cookie cookie1: cookies) {
                final String sBill=cookie1.getName();
                if(sBill.equals("ssaccount")) {
                    Double BILL = Double.parseDouble(cookie1.getValue()) - Double.parseDouble(bill);
                        user.setWallet(BILL);
                        int res = userService.Deduct(user);
                        if (res==1) {
                            User userInfo = userForRedisService.findUserInfo(user.getUname());
                            if (!Objects.isNull(userInfo)){
                                userInfo.setWallet(BILL);
                                userForRedisService.insertUserInfo(userInfo);
                            }
                            logger.info(res);
                            Cookie cookie = new Cookie("ssaccount",BILL.toString());
                            cookie.setPath("/");
                            cookie.setMaxAge(60*60*24);
                            response.addCookie(cookie);
                            return "0";
                        } else {
                            logger.error(res);
                            return "1";
                        }
                    }
            }
        }
        else return "1";
        return 0;
    }
}
