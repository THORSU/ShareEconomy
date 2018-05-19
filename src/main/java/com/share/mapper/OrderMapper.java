package com.share.mapper;

import com.share.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    List<Orders> getOrdersByUserName(@Param("userName") String username);

    List<Orders> insertOrdersByuserName(@Param("userName") String username);
}
