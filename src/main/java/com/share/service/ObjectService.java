package com.share.service;

import com.share.pojo.Object_1;

import java.util.List;

/**
 * Created by weixin on 17-8-6.
 */

public interface ObjectService {
    public int IncObj(Object_1 object);

    public Object_1 ObjInfo(Object_1 object);

    String searchGoodsName(String context);
}
