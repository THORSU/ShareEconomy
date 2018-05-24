package com.share.pojo;

/**
 * @author: QuincySu
 * @Date: 2018/5/21
 */
public class Merchant {
    //商家id
    private String id;
    //商家名
    private String merchantName;
    //商家密码
    private String merchantPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }
}
