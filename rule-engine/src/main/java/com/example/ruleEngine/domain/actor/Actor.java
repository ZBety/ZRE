package com.example.ruleEngine.domain.actor;

import com.example.ruleEngine.domain.io.OutputSlot;
import com.example.ruleEngine.domain.layout.EndpointModel;
import com.example.ruleEngine.msg.DataMsg;

public interface Actor {

    void init();

    void start();

    void stop();

    RunningState status();

    String getId();

    String getParentId();

    boolean isDiagram();
}

interface EndpointProvider {

    public DataMsg getInput(String name);

    public DataMsg getInput(EndpointModel endpoint);

    public OutputSlot<?> getOutput(EndpointModel endpoint);

    public OutputSlot<?> getOutput(String name);
}
