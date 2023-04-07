package com.example.ruleEngine.plugin.management.domain;

public interface PluginContext {

    String addBean(Object clazz);

    String addBean(String name, Object clazz);

    Object getBean(String name);

    PluginContext init();
}
