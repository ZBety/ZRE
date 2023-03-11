package com.example.ruleEngine.engine;

import com.example.ruleEngine.config.RuleEngineConfiguration;
import com.example.ruleEngine.domain.actor.RunningState;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.template.AgeDiscountRule;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;

public class DiscountRuleEngine implements RuleEngine<DiagramRuleModel>{

    String name;

    ApplicationContext ctx;

    RuleEngineConfiguration config;

    private RunningState runningState = RunningState.UNKNOWN;
    private HashMap<String, DiagramRuleModel> actors = new HashMap<>();

    public DiscountRuleEngine(String name, ApplicationContext ctx, RuleEngineConfiguration config) {
        this.name = name;
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    public RunningState status() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadRule(List<DiagramRuleModel> rules) {
        rules.forEach(this::loadRule);
    }

    @Override
    public void loadRule(DiagramRuleModel rule) {
        System.out.println("success load rule: " + rule);
        actors.put(rule.getId(), rule);
    }

    @Override
    public List<DiagramRuleModel> getLoadedRule() {
        return null;
    }

    @Override
    public void unloadRule(List<DiagramRuleModel> rules) {

    }

    @Override
    public void unloadRUle(DiagramRuleModel rule) {

    }

    @Override
    public RuleEngineContext getContext() {
        return null;
    }

    public RunningState getRunningState() {
        return runningState;
    }

    public void setRunningState(RunningState runningState) {
        this.runningState = runningState;
    }

    public HashMap<String, DiagramRuleModel> getActors() {
        return actors;
    }

    public void setActors(HashMap<String, DiagramRuleModel> actors) {
        this.actors = actors;
    }
}
