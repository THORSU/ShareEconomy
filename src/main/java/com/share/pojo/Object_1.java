package com.share.pojo;

import java.io.Serializable;

/**
 * Created by weixin on 17-8-6.
 */
public class Object_1 implements Serializable {
    //todo 此处设置了商品的id自增
    private String id;//产品id
    private String objectCode;//商品编码
    private String merchantName;//商家名
    private String objectName;//商品名称
    private String objectPrice;//商品价格
    private String objectStatus;//商品上下架状态（0:未上架 1:待审核）
    private String objectRemark;//商品备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectPrice() {
        return objectPrice;
    }

    public void setObjectPrice(String objectPrice) {
        this.objectPrice = objectPrice;
    }

    public String getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(String objectStatus) {
        this.objectStatus = objectStatus;
    }

    public String getObjectRemark() {
        return objectRemark;
    }

    public void setObjectRemark(String objectRemark) {
        this.objectRemark = objectRemark;
    }
}

