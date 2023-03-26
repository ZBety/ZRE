package com.example.ruleEngine.controller;


import com.example.ruleEngine.domain.AuditAOP;
import com.example.ruleEngine.domain.LoginRequest;
import com.example.ruleEngine.domain.ResponseModel;
import com.example.ruleEngine.service.AuthService;
import com.example.ruleEngine.service.UserService;
import com.example.ruleEngine.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "用户鉴权接口")
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        if (!userService.login(loginRequest)) {
            return ResponseEntity.ok("Incorrect username or password");
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return ResponseEntity.ok(new ResponseModel(data, "登录成功！", 200));
    }

    @GetMapping("/roles")
    public ResponseEntity<ResponseModel> getRoles() {
        return ResponseEntity.ok(new ResponseModel(authService.getRoles(), "success！", 200));
    }
}

