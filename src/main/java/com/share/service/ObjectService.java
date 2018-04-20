package com.share.service;

import com.share.pojo.Object_1;

/**
 * Created by weixin on 17-8-6.
 */

public interface ObjectService {
    public int IncObj(Object_1 object);

    public Object_1 ObjInfo(Object_1 object);

    Object_1[] searchGoodsName(String context);
}
