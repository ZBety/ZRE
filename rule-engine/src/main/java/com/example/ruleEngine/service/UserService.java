package com.example.ruleEngine.service;

import com.example.ruleEngine.domain.LoginRequest;
import com.example.ruleEngine.domain.User;
import com.example.ruleEngine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public String create(User user){
        if (userRepo.findByUsername(user.getUsername())!=null)
            return "创建失败，用户已存在！";
        user.setId(UUID.randomUUID().toString());
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setCreateTime(System.currentTimeMillis());
        userRepo.save(user);
        return "创建成功！";
    }

    public String delete(String userId) {
        userRepo.deleteById(userId);
        return "删除成功！";
    }

    public List<User> getAllUser() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void update(User user) {
        User oldUser = userRepo.findById(user.getId()).get();
        if (!oldUser.getPassword().equals(user.getPassword())) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
        }
        userRepo.save(user);
    }

    public User getUserDetail(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().getValue()));
    }
}
