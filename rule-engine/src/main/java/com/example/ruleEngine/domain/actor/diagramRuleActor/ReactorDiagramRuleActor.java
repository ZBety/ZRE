package com.example.ruleEngine.domain.actor.diagramRuleActor;

import com.example.ruleEngine.domain.actor.ActorFactory;
import com.example.ruleEngine.domain.actor.NodeActor;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.EndpointModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;

import java.util.*;
import java.util.stream.Collectors;

public class ReactorDiagramRuleActor extends NodeActor<DiagramRuleModel, DiagramRuleModel> {

    RuleEngineContext ctx;

    DiagramRuleModel ruleModel;

    public ReactorDiagramRuleActor(RuleEngineContext ctx, DiagramRuleModel ruleModel) {
        super(ctx, ruleModel, ruleModel);
        this.ctx = ctx;
        this.ruleModel = ruleModel;
        init();
    }

    private HashMap<String, NodeActor<?, ?>> nodeActors = new HashMap<>();


    public void init() {
        initLayout();
        initIo();
    }

    @Override
    public void initIo() {
//        List<DataMsg> inputs = ruleModel.getInputs();
        HashMap<String, DataMsg> inputs = (HashMap<String, DataMsg>) ruleModel.getInputs()
                .stream().map(this::getInput).collect(Collectors.toMap(
                        it -> "123",
                        it -> it
                ));
        this.setInputs(inputs);
    }

    public void initLayout() {
        this.nodeActors = initNodeActors(ctx, ruleModel);
    }

    private HashMap<String, NodeActor<?, ?>> initNodeActors(RuleEngineContext ctx, DiagramRuleModel ruleModel) {
        Map<String, NodeActor<?, ?>> nodeActors = ruleModel.getNodes().stream()
                .map(node -> ActorFactory.getNodeActor(ctx, ruleModel, node))
                .collect(Collectors.toMap(nodeActor -> ruleModel.getName(), nodeActor -> nodeActor));
        return new HashMap<>(nodeActors);
    }

    public DiagramRuleModel getRuleModel() {
        return ruleModel;
    }

    public void setRuleModel(DiagramRuleModel ruleModel) {
        this.ruleModel = ruleModel;
    }

    public NodeActor<?, ?> getNodeActor(String id) {
        return this.nodeActors.get(id);
    }

    @Override
    public DataMsg getInput(EndpointModel endpoint) {
        NodeActor nodeActor = getNodeActor(endpoint.getNodeId());
        return nodeActor.getInput(endpoint);
    }
}
