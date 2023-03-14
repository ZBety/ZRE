package com.example.ruleEngine.domain.actor.node;

import com.example.ruleEngine.domain.actor.NodeActor;
import com.example.ruleEngine.domain.actor.node.template.AgeDiscountRuleTemplate;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.NodeRuleModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;

import java.util.HashMap;

public class AgeDiscountNodeActor extends NodeActor<AgeDiscountRuleTemplate, NodeRuleModel> {
    private AgeDiscountRuleTemplate nodeTemplate;

    public static final String NAME = "ALERT_EVENT";

    private double result;

    public AgeDiscountNodeActor(RuleEngineContext ctx, DiagramRuleModel diagramRuleModel, NodeRuleModel nodeRuleModel) {
        super(ctx, diagramRuleModel, nodeRuleModel);
        this.nodeTemplate = nodeRuleModel.getNodeTemplate(AgeDiscountRuleTemplate.class);
    }

    @Override
    protected void onHandle(DataMsg dataMsg) {
        HashMap<String, Object> map = dataMsg.dataAsMap();
        int age = (int) map.get("age");

        result = nodeTemplate.calculateDiscount(age);
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
