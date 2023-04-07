package org.sdk.actor;

import org.sdk.RunningState;

public interface Actor {

    void init();

    void start();

    void stop();

    RunningState status();

    String getId();

    String getParentId();

    boolean isDiagram();
}

