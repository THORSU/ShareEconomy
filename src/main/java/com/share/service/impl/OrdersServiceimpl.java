package com.share.service.impl;

import com.share.mapper.OrderMapper;
import com.share.pojo.Orders;
import com.share.service.OrdersService;
import groovy.util.logging.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrdersServiceimpl implements OrdersService {

    private static Logger log = Logger.getLogger(OrdersServiceimpl.class);
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Orders> getOrdersByUserName(String username) {
        try {
            List<Orders> ordersList = orderMapper.getOrdersByUserName(username);
            return ordersList;
        } catch (Exception e) {
            log.error("getOrderLise.error");
            return null;
        }
    }

    @Override
    public int insertOrdersByUserName(Orders orders) {
        return orderMapper.insertOrdersByUserName(orders);
    }

    @Override
    public int updateOrder(Orders orders) {
        return 0;
    }

//    @Override
//    public int updateOrder(Orders orders) {
//        return  orderMapper.updateOrder;
//    }

}
