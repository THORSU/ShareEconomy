package com.share.pojo;

/**
 * Created by weixin on 17-8-6.
 */
public class Object_1 {
    private String id;//产品id
    private String code;//产品编码
    private String name;//产品名称
    private String price;//每小时计费
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Object_1{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
