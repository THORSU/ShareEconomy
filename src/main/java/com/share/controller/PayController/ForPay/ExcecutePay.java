package com.share.controller.PayController.ForPay;

import javax.servlet.http.Cookie;

/**
 * Created by weixin on 17-8-30.
 */
public class ExcecutePay implements Runnable {
    private String time;
    private Cookie[] oCookies;
    private ExcecutePaying paying;

    public ExcecutePay(String time, Cookie[] oCookies) {
        this.time = time;
        this.oCookies = oCookies;
    }

    @Override
    public void run() {
        paying=new ExcecutePaying();
        paying.Pay(time,oCookies);
    }
}
