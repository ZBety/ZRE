package com.example.ruleEngine.domain.actor;

import com.example.ruleEngine.domain.DataModel;
import com.example.ruleEngine.domain.io.OutputSlot;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.EndpointModel;
import com.example.ruleEngine.domain.layout.RuleModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;

import java.util.HashMap;

public abstract class NodeActor<M extends DataModel, T extends RuleModel> implements Actor, EndpointProvider{

    RuleEngineContext ctx;

    DiagramRuleModel diagramRuleModel;

    T nodeRuleModel;

    HashMap<String, DataMsg> inputs = new HashMap<>();

    public NodeActor(RuleEngineContext ctx, DiagramRuleModel diagramRuleModel, T nodeRuleModel) {
        this.ctx = ctx;
        this.diagramRuleModel = diagramRuleModel;
        this.nodeRuleModel = nodeRuleModel;
    }

    protected RunningState runningState = RunningState.PENDING;


    protected void initLayout() {
//        nodeRuleModel
    }

    @Override
    public void init() {
        initLayout();
    }

    @Override
    public void start() {
//        DataMsg input = inputs.get(nodeRuleModel.getName());
//        onHandle(input);
    }

    @Override
    public void stop() {

    }

    public void initIo() {
//        nodeRuleModel.getInputs().stream().map().forEach();
    }

    @Override
    public RunningState status() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public boolean isDiagram() {
        return false;
    }

    @Override
    public DataMsg getInput(String name) {
        return inputs.get(name);
    }

    @Override
    public DataMsg getInput(EndpointModel endpoint) {
        return getInput(endpoint.getName());
    }

    @Override
    public OutputSlot<?> getOutput(EndpointModel endpoint) {
        return getOutput(endpoint.getName());
    }

    @Override
    public OutputSlot<?> getOutput(String name) {
        return null;
    }

    protected void onHandle(DataMsg dataMsg){};

    public HashMap<String, DataMsg> getInputs() {
        return inputs;
    }

    public void setInputs(HashMap<String, DataMsg> inputs) {
        this.inputs = inputs;
    }
}
