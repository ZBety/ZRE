package com.example.ruleEngine.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "规则版本管理接口")
/**
 * 规则引擎需要提供一些接口来管理规则版本，例如版本发布、版本回滚、版本比较、版本迁移等。这些接口可以通过Web页面或API方式提供。
 */
public class RuleVersionManagementController {

//    @Autowired
//    private HelloService helloService;

//    @PostMapping("/create")
//    @Operation(summary = "创建")
//    public Hello create(@RequestBody Hello hello) {
//        return helloService.savaHello(hello);
//    }
}
