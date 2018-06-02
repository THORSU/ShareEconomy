package com.share.mapper;

import com.share.pojo.Object_1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by weixin on 17-8-6.
 */
public interface ObjectMapper {
    int IncObj(Object_1 object);

    Object_1 ObjInfo(Object_1 object);

    List<Object_1> searchGoodsName(String context);

    List<Object_1> getObjectInfo();

    Object_1 getInfoByObjectId(@Param("objectId") String objectId);

    /**
     * 获取商品状态
     *
     * @param objectName
     * @return
     */
    public Object_1 getObjectStatusByName(String objectName);
}
