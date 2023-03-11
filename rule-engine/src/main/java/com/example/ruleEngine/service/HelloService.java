package com.example.ruleEngine.service;

import com.example.ruleEngine.domain.Hello;
import com.example.ruleEngine.repositories.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HelloService {

    @Autowired
    private HelloRepository helloRepo;

    public Hello savaHello(Hello hello) {

        hello.setId(UUID.randomUUID().toString());
        return helloRepo.save(hello);
    }

}
