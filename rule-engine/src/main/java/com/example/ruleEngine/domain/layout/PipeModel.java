package com.example.ruleEngine.domain.layout;

public class PipeModel {
    private EndpointModel from;
    private EndpointModel to;

    public PipeModel(EndpointModel from, EndpointModel to) {
        this.from = from;
        this.to = to;
    }

    public EndpointModel getFrom() {
        return from;
    }

    public void setFrom(EndpointModel from) {
        this.from = from;
    }

    public EndpointModel getTo() {
        return to;
    }

    public void setTo(EndpointModel to) {
        this.to = to;
    }

    public EndpointModel getInput() {
        return to;
    }

    public EndpointModel getOutput() {
        return from;
    }
}


