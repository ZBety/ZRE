package com.example.ruleEngine.domain;

public enum Role {

    ADMIN("ADMIN"),
    SYSTEM_ADMIN("SYSTEM_ADMIN"),
    USER("USER");

    String value;
    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return "ROLE_" + value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
