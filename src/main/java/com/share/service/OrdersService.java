package com.share.service;

import com.share.pojo.Orders;

import javax.servlet.http.Cookie;
import java.util.List;

public interface OrdersService {

    public List<Orders> getOrdersByUserName(String username);

    public int insertOrdersByUserName(Cookie[] cookies,String bill,String period);

    public int updateOrder(Orders orders);
}
