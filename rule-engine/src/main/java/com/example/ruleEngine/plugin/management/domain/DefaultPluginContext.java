package com.example.ruleEngine.plugin.management.domain;

import com.example.ruleEngine.engine.RuleEngineContext;

import java.util.HashMap;

public class DefaultPluginContext implements PluginContext{

    private RuleEngineContext ruleEngineCtx;

    private HashMap<String, Object> contextContainer = new HashMap<>();

    public DefaultPluginContext() {
    }

    public DefaultPluginContext(RuleEngineContext ruleEngineCtx) {
        this.ruleEngineCtx = ruleEngineCtx;
        init();
    }

    @Override
    public String addBean(Object clazz) {
        return addBean(clazz.getClass().getSimpleName(), clazz);
    }

    @Override
    public String addBean(String name, Object clazz) {
        contextContainer.put(name, clazz);
        return name;
    }

    @Override
    public Object getBean(String name) {
        return contextContainer.get(name);
    }

    @Override
    public PluginContext init() {
        addBean(ruleEngineCtx);
        return this;
    }
}
