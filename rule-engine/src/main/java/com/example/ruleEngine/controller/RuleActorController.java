package com.example.ruleEngine.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "规则执行接口")
@RequestMapping("/ruleActor")
/**
 * 规则引擎需要提供一些接口来执行规则，例如单个规则执行、批量规则执行、动态规则执行等。这些接口可以通过Web页面或API方式提供。
 */
public class RuleActorController {

//    @Autowired
//    private HelloService helloService;
//
//    @PostMapping("/create")
//    @Operation(summary = "创建")
//    public Hello create(@RequestBody Hello hello) {
//        return helloService.savaHello(hello);
//    }
}
