package com.share.pojo.po;

/**
 * Created by sweeney on 2018/5/26.
 */
public class SubObjectInfoPo {
    private Long objectId;//'商品主表对应id'

    private String code;//'子商品序列号'

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
