package com.share.service;

import com.share.pojo.Object_1;

import java.util.List;

/**
 * Created by weixin on 17-8-6.
 */

public interface ObjectService {
    int IncObj(Object_1 object);

    Object_1 ObjInfo(Object_1 object);

    String searchGoodsName(String context);

    List<Object_1> getObjectInfo();

    Object_1 getInfoByObjectId(String objectId);
}
