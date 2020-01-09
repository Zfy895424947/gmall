package com.gmall.user;

import com.gmall.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GmallUserApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        userService.getAllUser();
    }

}
