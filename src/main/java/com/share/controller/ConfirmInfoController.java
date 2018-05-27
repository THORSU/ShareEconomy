package com.share.controller;

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
import java.io.UnsupportedEncodingException;

/**
 * Created by weixin on 17-9-26.
 * 获取名字
 */
@Controller
@RequestMapping("/ef")
public class ConfirmInfoController {
    private static Logger logger=Logger.getLogger(ConfirmInfoController.class);
    @Autowired
    private UserService userService;
    private User user;
    @RequestMapping(value = "/getname.from",method = RequestMethod.POST)
    public @ResponseBody
    Object GetInfo(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {

        final Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (final Cookie Item:cookies) {
                final String sname=Item.getName();
                if(sname.equals("ssid")){
                    user=new User();
                    user.setUid(Item.getValue());
                    User res=userService.getName(user);
                    logger.info(res.getAlias());
//                    String tem = new String(res.getAlias().getBytes("UTF-8"),"ISO-8859-1");
                    return res.getAlias();
                }
            }
        }
       else return "0";
        return 0;
    }
}
