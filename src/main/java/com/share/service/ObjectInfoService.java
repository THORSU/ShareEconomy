package com.share.service;

import com.share.pojo.ObjectInfo;
import com.share.pojo.po.SubObjectInfoPo;

/**
 * Created by sweeney on 2018/5/26.
 */
public interface ObjectInfoService {

    ObjectInfo getSubObjectInfo(SubObjectInfoPo subObjectInfoPo);

    int fixObject(SubObjectInfoPo objectInfoPo);
}
