package com.example.ruleEngine.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Hello {

    @Id
    private String id;

    private String hello;

    public Hello(String id, String hello) {
        this.id = id;
        this.hello = hello;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
