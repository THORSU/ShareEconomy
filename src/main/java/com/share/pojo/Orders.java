package com.share.pojo;

import java.math.BigDecimal;

/**
 * Created by weixin on 18-4-21.
 */
public class Orders {

    private Long id;//主键

    private String userName;//'用户名（手机号)'

    private Long objectId;//'商品主表对应id'

    private Long subObjectId;//'商品子表id'

    private String orderCode;//'订单号'

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    private BigDecimal bill;//'消费金额'

    private String period;//'使用时间'

    private String startTime;//'使用起始时间'

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private String endTime;//'使用结束时间'

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", objectId=" + objectId +
                ", subObjectId=" + subObjectId +
                ", order_code='" + orderCode + '\'' +
                ", bill=" + bill +
                ", period='" + period + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getSubObjectId() {
        return subObjectId;
    }

    public void setSubObjectId(Long subObjectId) {
        this.subObjectId = subObjectId;
    }



    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}
