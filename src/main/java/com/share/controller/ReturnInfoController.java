package com.share.controller;

import com.share.pojo.ObjectInfo;
import com.share.pojo.Object_1;
import com.share.pojo.po.SubObjectInfoPo;
import com.share.service.ObjectInfoService;
import com.share.service.ObjectService;
import com.share.util.DatetimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * Created by weixin on 17-8-14.
 */
@Controller
@RequestMapping("/cd")
public class ReturnInfoController {
    private static Logger logger = Logger.getLogger(ReturnInfoController.class);
    @Autowired
    private ObjectService objectService;
    @Autowired
    private ObjectInfoService objectInfoService;

    private Object_1 object_1;

    /**
     * 返回子商品信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ObjInfo.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object GetOInfo(HttpServletRequest request, HttpServletResponse response) {
        String objectId = request.getParameter("objectId").trim();
        String SubObjectCode = request.getParameter("subObjectCode").trim();
        SubObjectInfoPo subObjectInfoPo = new SubObjectInfoPo();
        subObjectInfoPo.setCode(SubObjectCode);
        subObjectInfoPo.setObjectId(Long.valueOf(objectId));
        ObjectInfo objectInfo = objectInfoService.getSubObjectInfo(subObjectInfoPo);
        if (!Objects.isNull(objectInfo)) {
            object_1 = objectService.getInfoByObjectId(objectId);
            if (!Objects.isNull(object_1)) {
                Cookie cookie = new Cookie("Obill", object_1.getObjectPrice());
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
            }
            if ("0".equals(objectInfo.getCondition())) {
                //主商品id加子商品Code加入cookie
                Cookie cookie = new Cookie("objectId&subObjectId&startTime", objectId + "&" + objectInfo.getId() + "&" + DatetimeUtil.currentDate("HH:mm:ss"));
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
                return objectInfo.getPassword();
            } else return "badObject";
        } else {
            return "null";
        }
    }

    /**
     * 首页返回所有商品信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getObjectInfo.from", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    List<Object_1> getObjectInfo(HttpServletRequest request, HttpServletResponse response) {
        return objectService.getObjectInfo();
    }


    @RequestMapping(value = "/fixObject.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object fixObject(HttpServletRequest request, HttpServletResponse response) {
        String objectId = request.getParameter("objectId").trim();
        String subObjectCode = request.getParameter("subObjectCode").trim();
        SubObjectInfoPo infoPo = new SubObjectInfoPo();
        if (StringUtils.isNotBlank(subObjectCode) && StringUtils.isNotBlank(objectId)) {
            infoPo.setObjectId(Long.valueOf(objectId));
            infoPo.setCode(subObjectCode);
        }
        int resTag = objectInfoService.fixObject(infoPo);
        if (resTag == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "/checkObjectStatus.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object checkObjectStatus(HttpServletRequest request, HttpServletResponse response) {
        String objectId = request.getParameter("objectId").trim();
        Object_1 res = objectService.getInfoByObjectId(objectId);
        if (Objects.isNull(res)) {
            return "status error";
        } else if (res.getObjectStatus().equals("0")) {
            return "status error";
        }
        return null;
    }
}
