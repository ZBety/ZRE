package com.example.ruleEngine.domain;

import lombok.Data;

@Data
public class AttributeDescribe {
    String name;
    String type;
    Object value;
    String description;
}
