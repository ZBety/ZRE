package com.example.ruleEngine.engine;

import com.example.ruleEngine.config.RuleEngineConfiguration;
import com.example.ruleEngine.service.Action;
import org.springframework.context.ApplicationContext;

public class RuleEngineContext {

    private String name;

    private ApplicationContext appCtx;

    private RuleEngineConfiguration config;


    public RuleEngineContext(String name, ApplicationContext appCtx, RuleEngineConfiguration config) {
        this.name = name;
        this.appCtx = appCtx;
        this.config = config;
    }

    public <T> T getBean(Class<T> requestedType) {
        return appCtx.getBean(requestedType);
    }

    public <T> T getBean(String name, Class<T> requestedType) {
        return appCtx.getBean(name, requestedType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicationContext getAppCtx() {
        return appCtx;
    }

    public void setAppCtx(ApplicationContext appCtx) {
        this.appCtx = appCtx;
    }

    public RuleEngineConfiguration getConfig() {
        return config;
    }

    public void setConfig(RuleEngineConfiguration config) {
        this.config = config;
    }
}
