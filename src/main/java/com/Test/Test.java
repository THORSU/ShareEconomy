package com.Test;

import com.share.pojo.User;
import com.share.service.impl.UserServiceimpl;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by weixin on 17-8-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/**/applicationContext*.xml"}, loader = GenericXmlContextLoader.class)
@Transactional
public class Test {

    @Autowired
    private UserServiceimpl userService;

    @org.junit.Test
    public void login() throws Exception {
        User user = new User();
        user.setUname("uname");
        user.setUpwd("123456789");
        List<User> userInfo = (List<User>) userService.login(user);
        Assert.assertEquals(1, userInfo.size());
    }
}
