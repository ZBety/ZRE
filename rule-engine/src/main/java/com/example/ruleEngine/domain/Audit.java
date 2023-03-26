package com.example.ruleEngine.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
public class Audit {

    @Id
    private String id;

    private String handlers;

    private String action;

    private String remoteAddress;

    private String params;

    private String result;

    private long creatTime;

    public Audit(String handlers, String action, String remoteAddress, String params) {
        this.id = UUID.randomUUID().toString();
        this.handlers = handlers;
        this.action = action;
        this.remoteAddress = remoteAddress;
        this.params = params;
        this.creatTime = System.currentTimeMillis();
    }
}
