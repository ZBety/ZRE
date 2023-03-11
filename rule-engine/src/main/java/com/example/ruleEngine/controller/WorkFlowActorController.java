package com.example.ruleEngine.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "工作流执行接口")
/**
 * 工作流管理系统需要提供一些接口来执行任务，例如单个任务执行、批量任务执行、动态任务执行等。这些接口可以通过Web页面或API方式提供。
 */
public class WorkFlowActorController {

//    @Autowired
//    private HelloService helloService;

//    @PostMapping("/create")
//    @Operation(summary = "创建")
//    public Hello create(@RequestBody Hello hello) {
//        return helloService.savaHello(hello);
//    }
}
