package com.example.ruleEngine.domain.actor.node;

import com.example.ruleEngine.domain.actor.node.template.AgeDiscountRuleTemplate;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.NodeRuleModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import org.sdk.actor.NodeActor;
import org.sdk.io.OutputSlot;
import org.sdk.msg.DataMsg;

import java.util.HashMap;

public class AgeDiscountNodeActor extends NodeActor<AgeDiscountRuleTemplate, NodeRuleModel> {
    private final AgeDiscountRuleTemplate nodeTemplate;

    public static final String NAME = "AGE_DISCOUNT_RULE";

    private Object result;

    public AgeDiscountNodeActor(RuleEngineContext ctx, DiagramRuleModel diagramRuleModel, NodeRuleModel nodeRuleModel) {
        super(nodeRuleModel);
        this.nodeTemplate = nodeRuleModel.getNodeTemplate(AgeDiscountRuleTemplate.class);
    }

    @Override
    protected void onHandle(DataMsg dataMsg) {
        HashMap<String, Object> map = dataMsg.dataAsMap();

        int age = (int) map.get("age");
        double amount = Double.parseDouble(map.get("amount").toString());
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
