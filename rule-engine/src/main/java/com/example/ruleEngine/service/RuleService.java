package com.example.ruleEngine.service;

import com.example.ruleEngine.application.DiscountRuleEngineApplication;
import com.example.ruleEngine.domain.rules.RuleData;
import com.example.ruleEngine.repositories.RuleDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {

    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);
    @Autowired
    private DiscountRuleEngineApplication engine;

    @Autowired
    private RuleDataRepository ruleDataRepo;

    public String creatRule(RuleData ruleData) {
        try {
            engine.loadRule(ruleData)
                    .start();
            ruleDataRepo.save(ruleData);
        } catch (Exception e) {
            logger.error("load rule is failed");
        }
        return "success!";
    }

    public void updateRule(RuleData ruleData) {
        try {
            engine.loadRule(ruleData)
                    .start();
            ruleDataRepo.save(ruleData);
        } catch (Exception e) {
            logger.error("load rule is failed");
        }
    }
}
