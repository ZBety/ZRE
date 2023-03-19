package com.example.ruleEngine.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModelProperty {
    String defaultValue() default "";
    String description() default "";
}
