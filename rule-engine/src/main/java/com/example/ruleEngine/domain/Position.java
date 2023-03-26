package com.example.ruleEngine.domain;

import lombok.Data;

@Data
public class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
    }
}
