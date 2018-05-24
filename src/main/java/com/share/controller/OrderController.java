package com.share.controller;


import com.share.pojo.Orders;
import com.share.service.OrdersService;
import com.share.util.RandomAccessUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        String username = request.getParameter("username").trim();
        List<Orders> ordersList = ordersService.getOrdersByUserName(username);
        return ordersList;
    }

    @RequestMapping(value = "/insertOrder.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object insertOrdersByUserName(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("uesrname").trim();
        String objectid = request.getParameter("objectId").trim();
        String subObject = request.getParameter("subObjectId").trim();
        String bill = request.getParameter("bill").trim();
        String period = request.getParameter("period").trim();
        orders = new Orders();
        orders.setUserName(username);
        orders.setObjectId(Long.valueOf(objectid));
        orders.setSubObjectId(Long.valueOf(subObject));
        orders.setBill(BigDecimal.valueOf(0));
        orders.setOrder_code(RandomAccessUtil.getRandom("order_code"));
        orders.setBill(BigDecimal.valueOf(Long.parseLong(bill)));
        orders.setPeriod(period);
        int res = ordersService.insertOrdersByUserName(orders);
        if (res == 1) {
            logger.info(res);
            return "1";
        } else {
            logger.error(res);
            return "0";
        }
    }

}
