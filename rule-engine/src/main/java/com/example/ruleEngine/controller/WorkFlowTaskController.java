package com.example.ruleEngine.controller;

import com.example.ruleEngine.domain.ResponseModel;
import com.example.ruleEngine.service.DiagramTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "工作流任务接口")
@RequestMapping("/workFlowTask")
/**
 * 工作流管理系统需要提供一些接口来定义任务，例如任务类型、任务输入参数、任务输出结果等。这些接口可以通过Web页面或API方式提供。
 */
public class WorkFlowTaskController {

    @Autowired
    private DiagramTaskService taskService;

    @PostMapping("/execute")
    @Operation(summary = "执行流程")
    public ResponseEntity<?> execute(@RequestBody Object data) {
        System.out.println(data.toString());
        taskService.execute(data);
        return ResponseEntity.ok(new ResponseModel(null,"success",200));
    }
}
