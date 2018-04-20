package com.share.pojo;


import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by weixin on 17-8-30.
 */
public class Bill {
    public volatile static double bill;
//    private static double bill2;
//
//    public void setBill(double bill) {
//        bill=this.bill;
//
////        System.out.println(this.getBill());
//    }
    public double getBill(){
        System.out.println(bill+"-------------000--------------");
        return bill;
    }

}
