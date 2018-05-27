package com.share.service.impl;

import com.share.mapper.ObjectInfoMapper;
import com.share.mapper.OrderMapper;
import com.share.pojo.Orders;
import com.share.service.OrdersService;
import com.share.util.DatetimeUtil;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class OrdersServiceimpl implements OrdersService {

    private static Logger log = Logger.getLogger(OrdersServiceimpl.class);
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ObjectInfoMapper objectInfoMapper;

    @Override
    public List<Orders> getOrdersByUserName(String username) {
        try {
            List<Orders> ordersList = orderMapper.getOrdersByUserName(username);
            //添加子商品code
            if (!CollectionUtils.isEmpty(ordersList)){
                ordersList.forEach(item->{
                    String subCode = objectInfoMapper.getSubObjectCode(item.getSubObjectId());
                    if (StringUtils.isNotBlank(subCode)){
                        item.setSubObjectCode(subCode);
                    }
                });
            }
            return ordersList;
        } catch (Exception e) {
            log.error("getOrderLise.error");
            return null;
        }
    }

    @Override
    public int insertOrdersByUserName(Cookie[] cookies,String bill,String period) {
        String uesrname = null;
        String objectId = null;
        String subObjectId = null;
        String startTime = null;
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("ssname".equals(cookie.getName())) {
                    uesrname = cookie.getValue();
                }
                if ("objectId&subObjectCode&startTime".equals(cookie.getName())){
                    String[]  strings = cookie.getName().split("&");
                    objectId = strings[1];
                    subObjectId = strings[2];
                    startTime = strings[3];
                }
            }
        }
//
        Orders orders = new Orders();
        orders.setUserName(uesrname);
        if (StringUtils.isNotBlank(objectId) && StringUtils.isNotBlank(subObjectId)){
            orders.setObjectId(Long.valueOf(objectId));
            orders.setSubObjectId(Long.valueOf(subObjectId));
        }
        orders.setBill(BigDecimal.valueOf(Long.parseLong(bill)));
        orders.setOrderCode(Long.toString(System.currentTimeMillis()));
        orders.setPeriod(period);
        orders.setStartTime(startTime);
        orders.setEndTime(DatetimeUtil.currentDate("HH:mm:ss"));
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
