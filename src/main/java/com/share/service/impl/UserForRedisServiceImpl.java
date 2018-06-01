package com.share.service.impl;

import com.alibaba.fastjson.JSON;
import com.share.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by sweeney on 2018/5/31.
 */
@Service
public class UserForRedisServiceImpl implements com.share.service.UserForRedisService{
    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void insertUserInfo(User user) {
        HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("User",user.getUname(), JSON.toJSONString(user));
    }

    @Override
    public User findUserInfo(String userName) {
        HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();
        User user = JSON.parseObject(hashOperations.get("User",userName),User.class);
        return user;
    }
}
