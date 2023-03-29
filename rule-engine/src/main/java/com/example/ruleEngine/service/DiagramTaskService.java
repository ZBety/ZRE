 package com.example.ruleEngine.service;

import com.example.ruleEngine.application.DiagramRuleEngineApplication;
import com.example.ruleEngine.engine.DiagramRuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DiagramTaskService {

    @Autowired
    private DiagramRuleEngineApplication application;


    public void execute(Object data) {
        DiagramRuleEngine engine = application.getEngine();
        engine.execute(data);
    }

}
