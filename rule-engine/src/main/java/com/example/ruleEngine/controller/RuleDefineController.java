package com.example.ruleEngine.controller;

import com.example.ruleEngine.domain.Hello;
import com.example.ruleEngine.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "规则定义接口")
@RequestMapping("/ruleDefine")
/**
 * 规则引擎需要提供一些接口来定义规则。这些接口可以通过Web页面或API方式提供。在接口设计时，需要考虑规则的输入参数、输出结果、规则匹配条件等信息。
 */
public class RuleDefineController {

//    @Autowired
//    private HelloService helloService;

//    @PostMapping("/create")
//    @Operation(summary = "创建")
//    public Hello create(@RequestBody Hello hello) {
//        return helloService.savaHello(hello);
//    }
}
