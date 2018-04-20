package com.share.controller.ManagerController;

import com.share.pojo.Manager;
import com.share.service.ManagerService;
import com.share.service.ObjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by weixin on 17-8-9.
 */
@Controller
@RequestMapping("/ab")
public class MloginController {
    private static Logger logger=Logger.getLogger(MloginController.class);
    /**
     *
     */
    @Autowired
    private ManagerService managerService;
    private Manager manager;
    @RequestMapping(value = "/Mlogin.from",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object GetManager(HttpServletRequest request,HttpServletResponse response ){
        String mname=request.getParameter("Mname").trim();
        String mpwd=request.getParameter("Mpwd").trim();
        manager=new Manager();
        manager.setMname(mname);
        manager.setMpassword(mpwd);
        Manager res=managerService.Mlogin(manager);
        if(res!=null){
            logger.info(res);
            return "1";
        }
        else{
            logger.error("0");
            return "0";
        }

    }



}
