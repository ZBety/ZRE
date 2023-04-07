package com.example.ruleEngine.controller;

import com.example.ruleEngine.domain.AuditAOP;
import com.example.ruleEngine.domain.Hello;
import com.example.ruleEngine.domain.ResponseModel;
import com.example.ruleEngine.domain.UserCoupons;
import com.example.ruleEngine.repositories.UserCouponsRepository;
import com.example.ruleEngine.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "展示接口")
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private UserCouponsRepository ucReps;

    @GetMapping("/getCoupons")
    @Operation(summary = "获取用户优惠卷")
    public ResponseEntity<ResponseModel> getCoupons() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Object data = ucReps.findAllByUsername(username);
        return ResponseEntity.ok(new ResponseModel(data, "success", 200));
    }
}
