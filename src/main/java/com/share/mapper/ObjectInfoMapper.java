package com.share.mapper;

import com.share.pojo.ObjectInfo;
import com.share.pojo.po.SubObjectInfoPo;

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
}
