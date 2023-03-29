package com.example.ruleEngine.domain.actor.diagramRuleActor;

import com.example.ruleEngine.domain.actor.ActorFactory;
import com.example.ruleEngine.domain.actor.NodeActor;
import com.example.ruleEngine.domain.actor.RunningState;
import com.example.ruleEngine.domain.io.InputSlot;
import com.example.ruleEngine.domain.io.OutputSlot;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.Edge;
import com.example.ruleEngine.domain.layout.EndpointModel;
import com.example.ruleEngine.domain.layout.PipeModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class DiagramRuleActor extends NodeActor<DiagramRuleModel, DiagramRuleModel> {

    private RuleEngineContext ctx;

    private DiagramRuleModel ruleModel;

    private RunningState runningState = RunningState.PENDING;

    private final static Logger logger = LoggerFactory.getLogger(DiagramRuleActor.class);

    public DiagramRuleActor(RuleEngineContext ctx, DiagramRuleModel ruleModel) {
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

    public void initIo() {
        logger.info("初始化节点IO， 图表id："+ ruleModel.getId());
        ruleModel.getEdges().forEach(this::connectIO);

        logger.info("初始化图表IO， 图表id："+ ruleModel.getId());
    }

    public void initLayout() {
        this.nodeActors = initNodeActors(ctx, ruleModel);
    }

    private HashMap<String, NodeActor<?, ?>> initNodeActors(RuleEngineContext ctx, DiagramRuleModel ruleModel) {
        Map<String, NodeActor<?, ?>> nodeActors = ruleModel.getNodes().stream()
                .map(node -> ActorFactory.getNodeActor(ctx, ruleModel, node))
                .collect(Collectors.toMap(NodeActor::getId, nodeActor -> nodeActor));
        return new HashMap<>(nodeActors);
    }

    private void connectIO(Edge it) {
        NodeActor<?,?> source = getNodeActor(it.getSource());
        NodeActor<?,?> target = getNodeActor(it.getTarget());

        OutputSlot<DataMsg> outputSlot = source.getOutputs();
        InputSlot<DataMsg> inputSlot = target.getInputs();
//        target.addInputs(inputSlot);
        outputSlot.connect(inputSlot);
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
    
    public NodeActor<?, ?> getBegin() {
        return this.nodeActors.values()
                .stream()
                .filter(NodeActor::isBegin)
                .findFirst()
                .get();
    }

}
