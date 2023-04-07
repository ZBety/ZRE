package com.example.ruleEngine.controller;

import com.example.ruleEngine.domain.AuditAOP;
import com.example.ruleEngine.domain.ResponseModel;
import com.example.ruleEngine.domain.User;
import com.example.ruleEngine.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "用户操作接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','SYSTEM_ADMIN')")
    public ResponseEntity<?> getAllUser() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(new ResponseModel(users,"查询成功！", 200));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN','SYSTEM_ADMIN')")
    @AuditAOP(action = "新增用户")
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.create(user);
        return ResponseEntity.ok(new ResponseModel(null, "创建成功",200));
    }

    @PutMapping("/update")
    @AuditAOP(action = "修改用户")
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok(new ResponseModel(null, "修改成功！", 200));
    }

    @DeleteMapping("/delete/{id}")
    @AuditAOP(action = "删除用户")
    public ResponseEntity<?> delete(@PathVariable String id) {
        System.out.println(id);
        return ResponseEntity.ok(new ResponseModel(null, userService.delete(id), 200));
    }

    @GetMapping("/getDetail")
    public ResponseEntity<?> getDetail() {
        return ResponseEntity.ok(new ResponseModel(userService.getUserDetail(), "success", 200));
    }

}
