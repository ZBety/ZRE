package com.example.ruleEngine.domain.layout;

import lombok.Data;

@Data
public class EndpointModel {
    private String name;
    private String nodeId;
    private Object type;

    public EndpointModel() {
        this.name = "default";
        this.nodeId = null;
        this.type = null;
    }
    public EndpointModel(String name, String nodeId, Object type) {
        this.name = name;
        this.nodeId = nodeId;
        this.type = type;
    }
}
