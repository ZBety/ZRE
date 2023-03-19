package com.example.ruleEngine.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Template {
    String name() default "";
    String Type() default "";

    String description() default "";
}
