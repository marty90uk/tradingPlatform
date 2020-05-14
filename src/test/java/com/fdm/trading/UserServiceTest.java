package com.fdm.trading;

import com.fdm.trading.domain.User;
import com.fdm.trading.service.userServiceImpl.UserService;
import com.fdm.trading.service.userServiceImpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void testUserService(){
        User user = new User();
        user = userService.findByUsername("mhe60");
        System.out.println(user.toString());
        Assert.assertEquals(user.getPassword(), "");
    }
}
