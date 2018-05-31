package com.share.mapper;

import com.share.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    List<Orders> getOrdersByUserName(@Param("username") String uesrname);

    public int insertOrdersByUserName(Orders orders);

    public int updateOrder(Orders orders);

}
