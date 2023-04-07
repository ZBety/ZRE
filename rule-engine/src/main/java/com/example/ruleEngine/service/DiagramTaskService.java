 package com.example.ruleEngine.service;

import com.example.ruleEngine.application.DiagramRuleEngineApplication;
import com.example.ruleEngine.engine.DiagramRuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DiagramTaskService {

    @Autowired
    @Lazy
    private DiagramRuleEngineApplication application;

    private DiagramRuleEngine engine;

    @PostConstruct
    public void init() {
        engine = application.getEngine();
    }

    public void execute(Object data) {
        engine.execute(data);
    }

}
