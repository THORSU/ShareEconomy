package com.share.controller;


import com.share.pojo.Orders;
import com.share.service.OrdersService;
import com.share.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {
    private static Logger logger = Logger.getLogger(OrderController.class);

    @Autowired
    private OrdersService ordersService;
    private Orders orders;

    @RequestMapping(value = "/getOrders.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<Orders> getOrdersByUserName(HttpServletRequest request, HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        String uesrname = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("ssname".equals(cookie.getName())) {
                    uesrname = cookie.getValue();
                }
            }
        }
//        String username = request.getParameter("username").trim();
        List<Orders> ordersList = ordersService.getOrdersByUserName(uesrname);
        return ordersList;
    }

    @RequestMapping(value = "/insertOrder.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody

    Object insertOrdersByUserName(HttpServletRequest request, HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        String uesrname = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("ssname".equals(cookie.getName())) {
                    uesrname = cookie.getValue();
                }
            }
        }
//        String  username = request.getParameter("username").trim();
//        String objectid = request.getParameter("objectId").trim().toString();
//        String subObject = request.getParameter("subObjectId").trim();
        String bill = request.getParameter("bill");
        String period = request.getParameter("period").trim();
        orders = new Orders();
        orders.setUserName(uesrname);
//        orders.setObjectId(Long.valueOf(objectid));
//        orders.setSubObjectId(Long.valueOf(subObject));
        orders.setBill(BigDecimal.valueOf(0));
        orders.setOrderCode(Long.toString(System.currentTimeMillis()));
        orders.setPeriod(period);
        orders.setStartTime(DatetimeUtil.currentDate("HH:mm:ss"));
        int res = ordersService.insertOrdersByUserName(orders);
        if (res == 1) {
            logger.info(res);
            return "insert successful!";
        } else {
            logger.error(res);
            return "insert fail!";
        }
    }
}
