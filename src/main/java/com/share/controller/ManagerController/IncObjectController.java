package com.share.controller.ManagerController;

import com.share.pojo.Object_1;
import com.share.service.ObjectService;
import com.sun.deploy.net.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixin on 17-8-14.
 */
@Controller
@RequestMapping("/ab")
public class IncObjectController {
    private static Logger logger=Logger.getLogger(IncObjectController.class);

    @Autowired
    private ObjectService objectService;
    private Object_1 object;
    @RequestMapping(value = "/IncObj.from",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    Object GetObject(HttpServletRequest request, HttpServletResponse response){
        String Oname=request.getParameter("Oname").trim();
        String Oamount=request.getParameter("Oamount").trim();
        String Oprice=request.getParameter("Oprice").trim();
        object=new Object_1();
        object.setOid("");
        object.setObject(Oname);
        object.setAmount(Oamount);
        object.setPrice(Oprice);
        object.setCondition("0");
        int res=objectService.IncObj(object);

        if(res == 1){
            logger.info(res);
            return "1";
        }
        else {
            logger.error(res);
            return "0";
        }
    }

}
