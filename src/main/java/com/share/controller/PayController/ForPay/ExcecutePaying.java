package com.share.controller.PayController.ForPay;

import com.share.pojo.Bill;

import javax.servlet.http.Cookie;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by weixin on 17-8-30.
 */
public class ExcecutePaying {


    private final ReentrantLock Lock = new ReentrantLock();//
    private  double Bill;

    public void Pay(String str,Cookie[] cookies) {
        Lock.lock();
        try {
//            Bill bill1 = new Bill();
            int hour = Integer.parseInt(str.substring(0, 2));
            int minute = Integer.parseInt(str.substring(3, 5));
            int second = Integer.parseInt(str.substring(6, 8));
//        logger.info(time);
            if (cookies != null) {
                for (final Cookie oItem : cookies) {//calculate the bill.
                    final String sName = oItem.getName();
                    if (sName.equals("Obill")) {
                        double bill = Double.parseDouble(oItem.getValue());
                        if (hour>0) {
                            Bill=hour*bill*2;
                        }
                        if (minute <= 60&&minute>31) {
                                Bill = Bill+bill * 2;
                        } else {
                                 Bill=Bill+bill;
                                }
                        com.share.pojo.Bill.bill=Bill;
                        }
                        }
                    }

        } finally {
            Lock.unlock();
        }
    }
}

