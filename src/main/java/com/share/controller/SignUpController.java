package com.share.controller;

import com.share.pojo.User;
import com.share.service.UserService;
import com.share.util.RandomAccessUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixin on 17-7-31.
 */
@Controller
@RequestMapping("/cd")
public class SignUpController {
    private static Logger logger = Logger.getLogger(SignUpController.class);
    @Autowired
    private UserService userService;
    private User user;

    @RequestMapping(value = "/SignUp.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object getUserResult(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String Aname=request.getParameter("Aname").trim();
        String usermobile = request.getParameter("usermobile").trim();
        String IDnumber = request.getParameter("IDnumber").trim();
        user = new User();
        user.setUid(RandomAccessUtil.getRandom("user"));//uuid
        user.setUname(username);
        user.setUpwd(password);
        user.setAlias(Aname);
        user.setUmobile(usermobile);
        user.setIDnumber(IDnumber);
        user.setWallet(0);
        user.setCondition("0");
        int res = userService.SignUp(user);
        if (res == 1)
        {
            logger.info(res);
            return "1";
        }
        else {
            logger.error(res);
            return "0";
        }
    }



}
