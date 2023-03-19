package com.example.ruleEngine.application;

import com.example.ruleEngine.config.RuleEngineConfiguration;
import com.example.ruleEngine.domain.actor.rules.RuleActor;
import com.example.ruleEngine.domain.rules.RuleData;
import com.example.ruleEngine.engine.DiscountRuleEngine;
import com.example.ruleEngine.repositories.RuleDataRepository;
import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DiscountRuleEngineApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DiscountRuleEngineApplication.class);

    @Autowired
    private static ApplicationContext ctx;

    @Autowired
    private static RuleEngineConfiguration config;

    @Autowired
    private RuleDataRepository ruleDataRepo;

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

        HashMap<String, Object> data = new HashMap<>();
        List<RuleData> rules = StreamSupport.stream(ruleDataRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        engine.loadRule(rules);
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

    public RuleActor<?, ?> getActor(String id) {
        return engine.getActor(id);
    }

    public RuleActor<?, ?> loadRule(RuleData ruleData) {
        engine.loadRule(ruleData);
        return getActor(ruleData.getRuleId());
    }

}
