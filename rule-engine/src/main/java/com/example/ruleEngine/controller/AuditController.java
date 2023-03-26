package com.example.ruleEngine.controller;

import com.example.ruleEngine.domain.ResponseModel;
import com.example.ruleEngine.repositories.AuditRepository;
import com.example.ruleEngine.util.StreamUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
@Tag(name = "审计接口")
public class AuditController {

    @Autowired
    private AuditRepository auditRepo;

    @GetMapping("/list")
    public ResponseEntity<ResponseModel> list() {
        List<?> list = StreamUtil.iterableToList(auditRepo.findAll());
        return ResponseEntity.ok(new ResponseModel(list, "success", 200));
    }
}
