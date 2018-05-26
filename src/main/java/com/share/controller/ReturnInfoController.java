package com.share.controller;

import com.share.pojo.ObjectInfo;
import com.share.pojo.Object_1;
import com.share.pojo.po.SubObjectInfoPo;
import com.share.service.ObjectInfoService;
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
    @Autowired
    private ObjectInfoService objectInfoService;

    /**
     * 返回子商品信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ObjInfo.from",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object GetOInfo(HttpServletRequest request, HttpServletResponse response){
        String objectId = request.getParameter("objectId").trim();
        String SubObjectCode = request.getParameter("subObjectCode").trim();
        SubObjectInfoPo subObjectInfoPo = new SubObjectInfoPo();
        subObjectInfoPo.setCode(SubObjectCode);
        subObjectInfoPo.setObjectId(Long.valueOf(objectId));
        //主商品id加子商品Code加入cookie
        Cookie cookie = new Cookie("objectId&subjectCode",objectId+"&"+SubObjectCode);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);

        return objectInfoService.getSubObjectInfo(subObjectInfoPo);
    }

    /**
     * 首页返回所有商品信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getObjectInfo.from",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    List<Object_1> getObjectInfo(HttpServletRequest request, HttpServletResponse response){
        return objectService.getObjectInfo();
    }
}
