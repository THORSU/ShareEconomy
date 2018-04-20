package com.share.controller;

import com.share.pojo.User;
import com.share.service.UserService;
import org.apache.ibatis.javassist.compiler.MemberResolver;
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
 * Created by weixin on 17-8-3.
 */
@Controller
@RequestMapping("/cd")
public class UpdateController {
    private static Logger logger = Logger.getLogger(UpdateController.class);
    @Autowired
    private UserService userService;
    private User user;

    @RequestMapping(value = "/UpdateInfo.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object Updateinfo(HttpServletRequest request, HttpServletResponse response) {
        String NewUpassword = request.getParameter("NewUpassword").trim();
        String Aname = null;
        try {
            Aname = new String(request.getParameter("Aname").getBytes("ISO-8859-1"),"UTF-8");//内容格式乱码，强转中文
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final Cookie[] oCookies = request.getCookies();
        if (oCookies != null) {
            for (final Cookie oItem : oCookies) {
                final String sName = oItem.getName();
                if (sName.equals("ssid")){
                    user = new User();
                    user.setUid(oItem.getValue());
                    user.setUpwd(NewUpassword);
                    user.setAlias(Aname);
                    int res = userService.UpdateInfo(user);
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
