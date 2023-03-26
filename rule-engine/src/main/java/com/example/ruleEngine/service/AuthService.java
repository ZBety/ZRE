package com.example.ruleEngine.service;

import com.example.ruleEngine.domain.Role;
import com.example.ruleEngine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    @Autowired
    private UserRepository userRepo;

    public Role[] getRoles() {
        return Role.values();
    }
}
