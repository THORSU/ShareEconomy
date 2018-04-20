package com.share.controller;

import com.share.pojo.User;
import com.share.service.UserService;
import org.apache.log4j.Logger;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixin on 17-8-4.
 */
@Controller
@RequestMapping("/cd")
public class CheckUnameController {
    private static Logger logger=Logger.getLogger(CheckUnameController.class);
    @Autowired
    private UserService userService;
    private User user;
    @RequestMapping(value = "/CheckUname.from",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public @ResponseBody
    Object CheckUname(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("username").trim();
        user=new User();
        user.setUname(username);
        User res=userService.CheckUname(user);

        if(res!=null){
            logger.error(res);
            return "1";
        }
        else {
            return "0";
        }
    }
}
