package com.example.ruleEngine.engine;

import com.example.ruleEngine.config.RuleEngineConfiguration;
import org.springframework.context.ApplicationContext;

public class RuleEngineContext {

    String name;

    ApplicationContext appCtx;

    RuleEngineConfiguration config;

    public RuleEngineContext(String name, ApplicationContext appCtx, RuleEngineConfiguration config) {
        this.name = name;
        this.appCtx = appCtx;
        this.config = config;
    }

    <T> T getBean(Class<T> requestedType) {
        return appCtx.getBean(requestedType);
    }

    <T> T getBean(String name, Class<T> requestedType) {
        return appCtx.getBean(name, requestedType);
    }

}
