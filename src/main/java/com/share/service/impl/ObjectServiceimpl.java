package com.share.service.impl;

import com.share.controller.ReturnInfoController;
import com.share.mapper.ObjectMapper;
import com.share.pojo.Object_1;
import com.share.service.ObjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weixin on 17-8-6.
 */
@Service
public class ObjectServiceimpl implements ObjectService {
    private static Logger logger=Logger.getLogger(ReturnInfoController.class);
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
    public String searchGoodsName(String context) {


        List<Object_1> res = objectMapper.searchGoodsName(context);
        if(res.size()!=0){
            StringBuffer temp = new StringBuffer();
            for (int i=0;i<res.size();i++){
                temp.append(res.get(i).getObjectName() + ",");//将搜索的结果的用逗号分开，加入到temp中
            }
            return temp.toString();//将搜索结果发给前段
        }

        else {
            return "0";
        }
    }

    @Override
    public List<Object_1> getObjectInfo() {
        try {
            List<Object_1> object_1List = objectMapper.getObjectInfo();
            if (!CollectionUtils.isEmpty(object_1List)) {
                return object_1List;
            }else {
                return new ArrayList<Object_1>();
            }
        }catch (Exception e){
            logger.error("商品查询失败！");
        }
        return new ArrayList<>();
    }

    @Override
    public Object_1 getInfoByObjectId(String objectId) {
        try {
            return objectMapper.getInfoByObjectId(objectId);
        }catch (Exception e){
            logger.error("查询失败！");
        }
        return null;
    }
}
