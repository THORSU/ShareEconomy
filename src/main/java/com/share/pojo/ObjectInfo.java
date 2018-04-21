package com.share.pojo;

/**
 * Created by weixin on 18-4-21.
 */
public class ObjectInfo {

    private Long id;//主键id

    private Long objectId;//'商品主表对应id'

    private String code;//'子商品序列号'

    private String password;//'子商品密码'

    private String condition;//'是否可用（好坏）'

    private String remark;//'商品备注'

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ObjectInfo{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                ", condition='" + condition + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
