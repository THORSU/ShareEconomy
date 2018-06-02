package com.share.mapper;

import com.share.pojo.ObjectInfo;
import com.share.pojo.po.SubObjectInfoPo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by sweeney on 2018/5/26.
 */
public interface ObjectInfoMapper {

    /**
     * 获取子商品
     * @param subObjectInfoPo
     * @return
     */
    ObjectInfo getSubObjectInfo(SubObjectInfoPo subObjectInfoPo);

    /**
     * 根据子商品id获取code
     * @param SubObjectId
     * @return
     */
    String getSubObjectCode (@Param("id") Long SubObjectId);

    /**
     * 报修某子商品
     * @param objectInfoPo
     */
    void fixObject(SubObjectInfoPo objectInfoPo);
}
