package com.example.ruleEngine.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rule-engine")
public class RuleEngineConfiguration {

    Boolean record = false;
}
