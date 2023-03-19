package com.example.ruleEngine.service;

import com.example.ruleEngine.domain.LoginRequest;
import com.example.ruleEngine.domain.User;
import com.example.ruleEngine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public boolean login(LoginRequest loginRequest) {
        User user = userRepo.findByUsername(loginRequest.getUsername());
        if (user==null)
            return false;
        return passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
    }

    public void create(User user){
        user.setId(UUID.randomUUID().toString());
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        userRepo.save(user);
    }

    public void updatePassword(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
