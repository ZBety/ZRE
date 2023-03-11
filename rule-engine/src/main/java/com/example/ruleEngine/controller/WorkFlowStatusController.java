package com.example.ruleEngine.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "工作流状态接口")
/**
 * 工作流管理系统需要提供一些接口来查询任务执行状态，例如任务执行成功、任务执行失败、任务执行超时等。这些接口可以通过Web页面或API方式提供。
 */
public class WorkFlowStatusController {

//    @Autowired
//    private HelloService helloService;

//    @PostMapping("/create")
//    @Operation(summary = "创建")
//    public Hello create(@RequestBody Hello hello) {
//        return helloService.savaHello(hello);
//    }
}
