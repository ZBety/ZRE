package com.example.ruleEngine.controller;

import com.example.ruleEngine.domain.AuditAOP;
import com.example.ruleEngine.domain.NodeDefinitionInfo;
import com.example.ruleEngine.domain.ResponseModel;
import com.example.ruleEngine.domain.rules.RuleData;
import com.example.ruleEngine.service.RuleService;
import com.example.ruleEngine.util.ScannerUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "规则定义接口")
@RequestMapping("/ruleDefine")
/**
 * 规则引擎需要提供一些接口来定义规则。这些接口可以通过Web页面或API方式提供。在接口设计时，需要考虑规则的输入参数、输出结果、规则匹配条件等信息。
 */
public class RuleDefineController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/create")
    @Operation(summary = "创建")
    public String create(@RequestBody RuleData ruleData) {
        return ruleService.creatRule(ruleData);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除规则")
    @AuditAOP(action = "删除规则")
    public ResponseEntity<ResponseModel> delete(String ruleId) {
        return ResponseEntity.ok(new ResponseModel("delete success!", "",200));
    }

    @PutMapping("/update")
    @Operation(summary = "更新规则")
    @AuditAOP(action = "更新规则")
    public ResponseEntity<ResponseModel> update(@RequestBody RuleData ruleData) {
        ruleService.updateRule(ruleData);
        return ResponseEntity.ok(new ResponseModel("update success!", "",200));
    }

    @GetMapping("/getTemplate")
    @Operation(summary = "查看规则节点模版")
    public ResponseEntity<?> getDefinition() {
        List<NodeDefinitionInfo> templates = ScannerUtil.findAllTemplate();
        return ResponseEntity.ok(new ResponseModel(templates, "获取成功！", 200));
    }

    @GetMapping("/getTypes")
    @Operation(summary = "查看规则节点类型")
    public ResponseEntity<?> getTypes() {
        List<String> templates = ScannerUtil.findAllType();
        return ResponseEntity.ok(new ResponseModel(templates, "获取成功！", 200));
    }
}
