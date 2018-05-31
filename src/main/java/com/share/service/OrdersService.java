package com.share.service;

import com.share.pojo.Orders;

import java.util.List;

public interface OrdersService {

    public List<Orders> getOrdersByUserName(String username);

    public int insertOrdersByUserName(Orders orders);

    public int updateOrder(Orders orders);
}
