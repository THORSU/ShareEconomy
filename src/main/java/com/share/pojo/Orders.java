package com.share.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by weixin on 18-4-21.
 */
public class Orders {

    private Long id;//主键

    private String userName;//'用户名（手机号)'

    private Long objectId;//'商品主表对应id'

    private Long subObjectId;//'商品子表id'

    private String order_code;//'订单号'

    private BigDecimal bill;//'消费金额'

    private String period;//'使用时间'

    private Date startTime;//'使用起始时间'

    private Date endTime;//'使用结束时间'

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", objectId=" + objectId +
                ", subObjectId=" + subObjectId +
                ", order_code='" + order_code + '\'' +
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

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
