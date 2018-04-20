package com.share.mapper;

import com.share.pojo.Object_1;

/**
 * Created by weixin on 17-8-6.
 */
public interface ObjectMapper {
    public int IncObj(Object_1 object);

    public Object_1 ObjInfo(Object_1 object);

    Object_1[] searchGoodsName(String context);
}
