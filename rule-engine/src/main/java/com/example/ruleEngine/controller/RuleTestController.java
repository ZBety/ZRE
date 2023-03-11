package com.example.ruleEngine.controller;

import com.example.ruleEngine.application.DiscountRuleEngineApplication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "规则测试接口")
@RequestMapping("/ruleTest")
/**
 * 规则引擎需要提供一些接口来测试规则，例如输入测试数据、查看测试结果、调试日志等。这些接口可以通过Web页面或API方式提供。
 */
public class RuleTestController {

    @Autowired
    private DiscountRuleEngineApplication engine;

    @GetMapping("/test")
    @Operation(summary = "测试")
    public double create(String id, int num, double amount) {
        return engine.getActor()
                .get(id)
                .calculateDiscount(num);
    }

    @GetMapping("/testEngine")
    @Operation(summary = "测试引擎注入")
    public Object test() {
        System.out.println(engine);
        return engine;
    }
}
