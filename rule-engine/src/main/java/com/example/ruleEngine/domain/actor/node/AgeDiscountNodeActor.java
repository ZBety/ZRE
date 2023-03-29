package com.example.ruleEngine.domain.actor.node;

import com.example.ruleEngine.domain.actor.NodeActor;
import com.example.ruleEngine.domain.actor.node.template.AgeDiscountRuleTemplate;
import com.example.ruleEngine.domain.io.OutputSlot;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.NodeRuleModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;
import lombok.Data;

import java.util.HashMap;

public class AgeDiscountNodeActor extends NodeActor<AgeDiscountRuleTemplate, NodeRuleModel> {
    private final AgeDiscountRuleTemplate nodeTemplate;

    public static final String NAME = "AGE_DISCOUNT_RULE";

    private Object result;

    public AgeDiscountNodeActor(RuleEngineContext ctx, DiagramRuleModel diagramRuleModel, NodeRuleModel nodeRuleModel) {
        super(ctx, diagramRuleModel, nodeRuleModel);
        this.nodeTemplate = nodeRuleModel.getNodeTemplate(AgeDiscountRuleTemplate.class);
    }

    @Override
    protected void onHandle(DataMsg dataMsg) {
        HashMap<String, Object> map = dataMsg.dataAsMap();

        int age = (int) map.get("age");
        double amount = (double) map.get("amount");
        this.result = nodeTemplate.calculateDiscount(age)*amount;
        System.out.println("年龄折扣规则节点运行结果："+result);
        OutputSlot<DataMsg> outputs = getOutputs();
        map.put("result", this.result);
        outputs.send(dataMsg);
    }

    public AgeDiscountRuleTemplate getNodeTemplate() {
        return nodeTemplate;
    }

    public Object getResult() {
        return result;
    }
}
