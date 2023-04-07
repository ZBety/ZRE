package com.example.ruleEngine;

import com.example.ruleEngine.domain.Coupons;
import com.example.ruleEngine.domain.Role;
import com.example.ruleEngine.domain.User;
import com.example.ruleEngine.repositories.CouponsRepository;
import com.example.ruleEngine.repositories.HelloRepository;
import com.example.ruleEngine.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RuleEngineApplicationTests {

//    @Autowired
//    private UserService userService;
    @Autowired
    private CouponsRepository repository;

    @Test
    void contextLoads() {
        Coupons coupons = new Coupons();
        coupons.setName("满500减200");
        coupons.setAmount(200.0);
        coupons.setAmountLevel(500.0);
        coupons.setType("500_200");
        repository.save(coupons);
    }

}
