package com.share.controller;


import com.share.pojo.Orders;
import com.share.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/getOrders.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<Orders> getOrdersByUserName(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username").trim();
        List<Orders> ordersList = ordersService.getOrdersByUserName(username);
        return ordersList;
    }
}
