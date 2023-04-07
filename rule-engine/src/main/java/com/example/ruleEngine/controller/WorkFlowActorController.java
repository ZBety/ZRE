package com.example.ruleEngine.controller;

import com.example.ruleEngine.application.DiagramRuleEngineApplication;
import com.example.ruleEngine.domain.ResponseModel;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.service.DiagramRuleModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "工作流执行接口")
@RequestMapping("/workFlowActor")
/**
 * 工作流管理系统需要提供一些接口来执行任务，例如单个任务执行、批量任务执行、动态任务执行等。这些接口可以通过Web页面或API方式提供。
 */
public class WorkFlowActorController {

    @Autowired
    private DiagramRuleModelService drmService;

    @Autowired
    private DiagramRuleEngineApplication application;

    @PostMapping("/create")
    @Operation(summary = "创建流程")
    public ResponseEntity<?> create(@RequestBody DiagramRuleModel diagram) {
        diagram.setId(UUID.randomUUID().toString());
        diagram.setNo(diagram.getId());
        drmService.create(diagram);
        application.getEngine().reload(diagram);
        return ResponseEntity.ok(new ResponseModel(null,"创建成功！", 200));
    }

    @GetMapping("/list")
    @Operation(summary = "流程列表")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(new ResponseModel(drmService.list(),"查询成功！", 200));
    }

    @GetMapping("/findById/{id}")
    @Operation(summary = "查询流程")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseModel(drmService.getDiagramById(id),"查询成功！", 200));
    }

    @PutMapping("/update")
    @Operation(summary = "更新流程")
    public ResponseEntity<?> update(@RequestBody DiagramRuleModel diagram) {
        drmService.updateDiagram(diagram);
        application.getEngine().reload(diagram);
        return ResponseEntity.ok(new ResponseModel(null,"更新成功！", 200));
    }

    @PutMapping("/updateStatus")
    @Operation(summary = "更新流程状态")
    public ResponseEntity<?> updateStatus(@RequestBody DiagramRuleModel diagram) {
        drmService.updateDiagram(diagram);
        application.getEngine().reload(diagram);
        return ResponseEntity.ok(new ResponseModel(null,"更新成功！", 200));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除流程")
    public ResponseEntity<?> delete(@PathVariable String id) {
        drmService.deleteDiagram(id);
        application.getEngine().unloadRule(id);
        return ResponseEntity.ok(new ResponseModel(null,"删除成功！", 200));
    }
}
