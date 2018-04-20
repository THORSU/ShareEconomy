package com.share.controller.PayController;

import com.share.pojo.Object_1;
import com.share.pojo.User;
import com.share.service.ObjectService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by weixin on 17-8-18.
 */
//@Controller
//@RequestMapping("/ef")
//public class PayingController {
//    private static Logger logger = Logger.getLogger(PayingController.class);
//
//    @RequestMapping(value = "/Paying.from", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    public
//    @ResponseBody
//    Double Pay(HttpServletResponse response, HttpServletRequest request) {
//        String time = request.getParameter("time").trim();
//        double Bill;
//        int hour = Integer.parseInt(time.substring(0, 2));
//        int minute = Integer.parseInt(time.substring(3, 5));
//        int second = Integer.parseInt(time.substring(6, 8));
//        logger.info(hour + minute + second);
////        logger.info(time);
//        final Cookie[] oCookies = request.getCookies();
//        if (oCookies != null) {
//            for (final Cookie oItem : oCookies) {//calculate the bill.
//                final String sName = oItem.getName();
//                if (sName.equals("Obill")) {
//                    Double bill = Double.parseDouble(oItem.getValue());
//                    if (minute <= 31) {
//                        Bill=bill;
//                        if (minute <= 60) {
//                            Bill=bill*2;
//                            if(hour>0){
//                                return hour*bill*2+Bill;
//                            }else
//                            return Bill;
//                        } else
//                        return Bill;
//                    }
//                }
//            }
//        }
//        else{
//        return 0.0;
//        }
//        return 0.0;
//    }
//}






