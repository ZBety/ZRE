package com.example.ruleEngine.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "工作流调度接口")
@RequestMapping("/workFlowDispatch")
/**
 * 工作流管理系统需要提供一些接口来调度任务，例如任务依赖关系、任务执行顺序、任务执行状态等。这些接口可以通过Web页面或API方式提供
 */
public class WorkFlowDispatchController {

//    @Autowired
//    private HelloService helloService;

//    @PostMapping("/create")
//    @Operation(summary = "创建")
//    public Hello create(@RequestBody Hello hello) {
//        return helloService.savaHello(hello);
//    }
}
