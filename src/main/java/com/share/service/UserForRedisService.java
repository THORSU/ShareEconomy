package com.share.service;

import com.share.pojo.User;

/**
 * Created by sweeney on 2018/5/31.
 */
public interface UserForRedisService {

    //新增用户
    void insertUserInfo(User user);

    //查询用户
    User findUserInfo(String userName);

    //


}
