package com.example.ruleEngine.engine;

import com.example.ruleEngine.domain.actor.RunningState;
import com.example.ruleEngine.domain.layout.RuleModel;

import java.util.List;

public interface RuleEngine<T extends RuleModel> {

    RunningState status();

    void start();

    void stop();

    void loadRule(List<T> rules);

    void loadRule(T rule);

    List<T> getLoadedRule();

    void unloadRule(List<T> rules);

    void unloadRUle(T rule);

    RuleEngineContext getContext();
}
