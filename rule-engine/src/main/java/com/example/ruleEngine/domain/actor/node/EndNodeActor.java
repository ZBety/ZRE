package com.example.ruleEngine.domain.actor.node;

import com.example.ruleEngine.domain.actor.NodeActor;
import com.example.ruleEngine.domain.actor.node.template.AgeDiscountRuleTemplate;
import com.example.ruleEngine.domain.actor.node.template.EndRuleTemplate;
import com.example.ruleEngine.domain.io.OutputSlot;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.NodeRuleModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;

import java.util.HashMap;

public class EndNodeActor extends NodeActor<EndRuleTemplate, NodeRuleModel> {
    private final EndRuleTemplate nodeTemplate;

    public static final String NAME = "END_RULE";

    private Object result;

    public EndNodeActor(RuleEngineContext ctx, DiagramRuleModel diagramRuleModel, NodeRuleModel nodeRuleModel) {
        super(ctx, diagramRuleModel, nodeRuleModel);
        this.nodeTemplate = nodeRuleModel.getNodeTemplate(EndRuleTemplate.class);
    }

    @Override
    protected void onHandle(DataMsg dataMsg) {
        HashMap<String, Object> map = dataMsg.dataAsMap();
        this.result = map.get("result");
        System.out.println("流程最终运行结果："+result);
    }

    public EndRuleTemplate getNodeTemplate() {
        return nodeTemplate;
    }

    public Object getResult() {
        return result;
    }
}
