package com.share.mapper;

import com.share.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by weixin on 17-7-31.
 */
public interface UserMapper {
    User getUserById(User user);

    User login(User user);

    int SignUp(User user);

    int UpdateInfo(User user);

    User CheckUname(@Param("uname") String uName);

    int reCharge(User user);
//    public int loginChangeCon(String id,int con);
    int loginChangeCon(User user);

    int Deduct(User user);

//    double IsZero(User user);

    User getName(User user);
}
