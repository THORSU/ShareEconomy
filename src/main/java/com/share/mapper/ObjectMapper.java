package com.share.mapper;

import com.share.pojo.Object_1;

import java.util.List;

/**
 * Created by weixin on 17-8-6.
 */
public interface ObjectMapper {
    public int IncObj(Object_1 object);

    public Object_1 ObjInfo(Object_1 object);

    List<Object_1> searchGoodsName(String context);
}
