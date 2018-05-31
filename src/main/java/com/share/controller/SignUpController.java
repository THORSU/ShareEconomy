package com.share.controller;

import com.share.pojo.User;
import com.share.service.UserForRedisService;
import com.share.service.UserService;
import com.share.util.RandomAccessUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by weixin on 17-7-31.
 */
@Controller
@RequestMapping("/cd")
public class SignUpController {
    private final static String CONDITION_FALSE = "0";
    private static Logger logger = Logger.getLogger(SignUpController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserForRedisService userForRedisService;
    private User user;

    @RequestMapping(value = "/SignUp.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object getUserResult(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        user = new User();
//        user.setUid(RandomAccessUtil.getRandom("user"));//uuid
        user.setUname(username);
        user.setUpwd(password);
        user.setAlias("");
        user.setUmobile(username);
        user.setIDnumber("");
        user.setWallet(0);
        user.setCondition(CONDITION_FALSE);
        int res = userService.SignUp(user);
        if (res == 1)
        {
            logger.info(res+"注册成功");
            return "1";
        }
        else if (res == 2){
            logger.error(res+"改用户名已被注册");
            return "2";
        }
        else {
            logger.error(res+"注册失败");
            return "0";
        }
    }

    @RequestMapping(value = "/CheckUname.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object checkUname(HttpServletRequest request, HttpServletResponse response) {
        String uName = request.getParameter("username").trim();
        if (StringUtils.isNoneBlank(uName)){
            User user = userForRedisService.findUserInfo(uName);//redis先查询
            if (Objects.isNull(user)){
                User userResult = userService.CheckUname(uName);
                if (userResult!=null){
                    return "1";
                }
            }
            else {return "1";}

        }
        return null;
    }
}
