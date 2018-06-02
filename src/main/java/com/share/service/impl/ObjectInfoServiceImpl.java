package com.share.service.impl;

import com.share.controller.ReturnInfoController;
import com.share.mapper.ObjectInfoMapper;
import com.share.pojo.ObjectInfo;
import com.share.pojo.po.SubObjectInfoPo;
import com.share.service.ObjectInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.Objects;

/**
 * Created by sweeney on 2018/5/26.
 */
@Service
public class ObjectInfoServiceImpl implements ObjectInfoService {
    private static Logger logger=Logger.getLogger(ReturnInfoController.class);
    @Autowired
    private ObjectInfoMapper objectInfoMapper;
    @Override
    public ObjectInfo getSubObjectInfo(SubObjectInfoPo subObjectInfoPo) {
        try {
            return objectInfoMapper.getSubObjectInfo(subObjectInfoPo);
        }catch (Exception e){
            logger.error("查询失败");
        }
        return new ObjectInfo();
    }

    @Override
    public int fixObject(SubObjectInfoPo objectInfoPo) {
        try {
            ObjectInfo objectInfo = objectInfoMapper.getSubObjectInfo(objectInfoPo);
            if (!Objects.isNull(objectInfo)){
                objectInfoMapper.fixObject(objectInfoPo);
                return 1;
            }
            else return 2;
        }
        catch (Exception e){
            logger.error("修改状态失败！");
            return 0;
        }
    }
}
