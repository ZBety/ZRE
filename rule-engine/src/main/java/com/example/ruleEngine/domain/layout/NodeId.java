package com.example.ruleEngine.domain.layout;

public class NodeId {
    private String no;

    public NodeId(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return no;
    }
}