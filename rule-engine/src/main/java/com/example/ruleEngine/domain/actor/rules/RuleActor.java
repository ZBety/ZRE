package com.example.ruleEngine.domain.actor.rules;

import com.example.ruleEngine.domain.DataModel;
import com.example.ruleEngine.domain.actor.RunningState;
import com.example.ruleEngine.domain.actor.node.template.AgeDiscountRuleTemplate;
import com.example.ruleEngine.domain.rules.RuleData;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;

import java.util.HashMap;

public abstract class RuleActor<M extends DataModel, T extends RuleData> {


    RuleEngineContext ctx;

    T ruleModel;

    HashMap<String, DataMsg> inputs = new HashMap<>();

    public RuleActor(RuleEngineContext ctx, T ruleModel) {
        this.ctx = ctx;
        this.ruleModel = ruleModel;
    }

    protected RunningState runningState = RunningState.PENDING;


    public void init() {
//        initLayout();
    }

    public void start() {
//        DataMsg input = inputs.get(nodeRuleModel.getName());
//        onHandle(input);
    }

    public void stop() {

    }

    public RunningState status() {
        return runningState;
    }

    protected void onHandle(DataMsg dataMsg){};

    public abstract Object execute(DataMsg dataMsg);

    public abstract AgeDiscountRuleTemplate getRuleTemplate();

}
