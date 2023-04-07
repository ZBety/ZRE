package org.sdk;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModelProperty {
    String defaultValue() default "";
    String description() default "";
    String component() default "Default";
    String defaultType() default "String";
}
