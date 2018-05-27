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

    @RequestMapping(value = "/getOrders.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<Orders> getOrdersByUserName(HttpServletRequest request, HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        String userName = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("ssname".equals(cookie.getName())) {
                    userName = cookie.getValue();
                }
            }
        }
        return ordersService.getOrdersByUserName(userName);

    }

    @RequestMapping(value = "/insertOrder.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody

    Object insertOrdersByUserName(HttpServletRequest request, HttpServletResponse response) {
        String bill = request.getParameter("bill");
        String period = request.getParameter("period").trim();
        final Cookie[] cookies = request.getCookies();

        int res = ordersService.insertOrdersByUserName(cookies,bill,period);
        if (res == 1) {
            logger.info(res);
            return "insert successful!";
        } else {
            logger.error(res);
            return "insert fail!";
        }
    }
}
