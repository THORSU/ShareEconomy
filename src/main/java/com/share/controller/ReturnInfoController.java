package com.share.controller;

import com.share.pojo.Object_1;
import com.share.service.ObjectService;
import org.apache.log4j.Logger;
import java.lang.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by weixin on 17-8-14.
 */
@Controller
@RequestMapping("/cd")
public class ReturnInfoController {
    private static Logger logger=Logger.getLogger(ReturnInfoController.class);
    @Autowired
    private ObjectService objectService;
    private Object_1 object1;
    @RequestMapping(value = "/ObjInfo.from",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object GetOInfo(HttpServletRequest request, HttpServletResponse response){
        String Oid=request.getParameter("Chioce").trim();
        object1=new Object_1();
//        object1.setOid(Oid);
        Object_1 res=objectService.ObjInfo(object1);


        if(res!=null){
            Cookie cookie4=new Cookie("Obill",res.getPrice());//make the price of this.id as one of the cookies.
            cookie4.setPath("/");
            cookie4.setMaxAge(60*60*24);
            response.addCookie(cookie4);

            logger.info(res);
//            return res.getPwd();
        }
        else {
            logger.error(res);
            return "0";
        }
        return null;
    }


    @RequestMapping(value = "/getObjectInfo.from",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    List<Object_1> getObjectInfo(HttpServletRequest request, HttpServletResponse response){
        return objectService.getObjectInfo();
    }
}
