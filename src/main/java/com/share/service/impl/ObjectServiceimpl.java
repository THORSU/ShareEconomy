package com.share.service.impl;

import com.share.mapper.ObjectMapper;
import com.share.pojo.Object_1;
import com.share.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
