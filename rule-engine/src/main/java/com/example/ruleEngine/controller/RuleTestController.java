package com.example.ruleEngine.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "规则测试接口")
/**
 * 规则引擎需要提供一些接口来测试规则，例如输入测试数据、查看测试结果、调试日志等。这些接口可以通过Web页面或API方式提供。
 */
public class RuleTestController {

//    @Autowired
//    private HelloService helloService;

//    @PostMapping("/create")
//    @Operation(summary = "创建")
//    public Hello create(@RequestBody Hello hello) {
//        return helloService.savaHello(hello);
//    }
}
