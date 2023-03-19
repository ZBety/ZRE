package com.example.ruleEngine.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class NodeDefinitionInfo {
    @Id
    String id;

    String name;

    List<AttributeDescribe> properties;

    String type;

    String description;

    public NodeDefinitionInfo() {
    }

    public NodeDefinitionInfo(String id, String name, List<AttributeDescribe> properties, String type, String description) {
        this.id = id;
        this.name = name;
        this.properties = properties;
        this.type = type;
        this.description = description;
    }
}
