package com.example.ruleEngine.controller;

import com.example.ruleEngine.domain.AuditAOP;
import com.example.ruleEngine.domain.Hello;
import com.example.ruleEngine.domain.ResponseModel;
import com.example.ruleEngine.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "测试接口")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @PostMapping("/create")
    @Operation(summary = "创建")
    @AuditAOP(action = "创建测试实例")
    public ResponseEntity<ResponseModel> create(@RequestBody Hello hello) {
        Object data = helloService.savaHello(hello);
        return ResponseEntity.ok(new ResponseModel(data, "success", 200));
    }
}
