package com.example.ruleEngine.application;

import com.example.ruleEngine.config.RuleEngineConfiguration;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.engine.DiscountRuleEngine;
import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DiscountRuleEngineApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DiscountRuleEngineApplication.class);

    @Autowired
    static ApplicationContext ctx;

    @Autowired
    private static RuleEngineConfiguration config;

    private static DiscountRuleEngine engine = new DiscountRuleEngine(DiscountRuleEngine.class.getSimpleName(), ctx, config);

    private Boolean status = false;

    @Override
    public void run(String... args) throws Exception {
        start();
        logger.info(engine.getClass().getSimpleName() + " started!");
    }

    @Synchronized()
    void start() {
        if (status) {
            throw new RuntimeException("DiscountRuleEngineApplication is already started!");
        } else {
            this.status = true;
        }

        //需要重构！
        DiagramRuleModel rule = new DiagramRuleModel("123", "ageDiscountRule", 18, 0.5);
        engine.loadRule(rule);
        try {
            engine.start();
        } catch (Exception e) {
            logger.error("引擎启动异常, message: '{}'", e.getMessage(), e);
        }
    }

    void stop() {
        engine.stop();
        this.status = false;
    }

    public Map<String, DiagramRuleModel> getActor() {
        return engine.getActors();
    }
}
