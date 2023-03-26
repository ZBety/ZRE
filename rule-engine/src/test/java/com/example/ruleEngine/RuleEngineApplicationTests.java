package com.example.ruleEngine;

import com.example.ruleEngine.domain.Role;
import com.example.ruleEngine.domain.User;
import com.example.ruleEngine.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RuleEngineApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setRole(Role.USER);
        userService.create(user);
    }

}
