package com.example.ruleEngine.domain;

import lombok.Data;

@Data
public class ResponseModel {
    private final Object data;

    private final String message;

    private final int code;

    public ResponseModel(Object data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
