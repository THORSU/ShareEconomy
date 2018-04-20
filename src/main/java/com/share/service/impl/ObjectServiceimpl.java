package com.share.service.impl;

import com.share.mapper.ObjectMapper;
import com.share.pojo.Object_1;
import com.share.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by weixin on 17-8-6.
 */
@Service
public class ObjectServiceimpl implements ObjectService {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public int IncObj(Object_1 object) {
        return objectMapper.IncObj(object);
    }

    @Override
    public Object_1 ObjInfo(Object_1 object) {
        return objectMapper.ObjInfo(object);
    }

    @Override
    public Object_1[] searchGoodsName(String context) {
        return objectMapper.searchGoodsName(context);
    }
}
