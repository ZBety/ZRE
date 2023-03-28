package com.example.ruleEngine.domain.actor.node;

import com.example.ruleEngine.domain.actor.NodeActor;
import com.example.ruleEngine.domain.actor.node.template.AgeDiscountRuleTemplate;
import com.example.ruleEngine.domain.actor.node.template.BeginRuleTemplate;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.NodeRuleModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;

import java.util.HashMap;

public class BeginNodeActor extends NodeActor<BeginRuleTemplate, NodeRuleModel> {
    private final BeginRuleTemplate nodeTemplate;

    public static final String NAME = "BEGIN_RULE";

    private Object result;

    public BeginNodeActor(RuleEngineContext ctx, DiagramRuleModel diagramRuleModel, NodeRuleModel nodeRuleModel) {
        super(ctx, diagramRuleModel, nodeRuleModel);
        this.nodeTemplate = nodeRuleModel.getNodeTemplate(BeginRuleTemplate.class);
    }

    @Override
    protected void onHandle(DataMsg dataMsg) {
//        HashMap<String, Object> map = dataMsg.dataAsMap();
//        int age = (int) map.get("age");
//        this.result = nodeTemplate;
        /**
         * 验证DataMsg数据是否合法
         */
    }

    public BeginRuleTemplate getNodeTemplate() {
        return nodeTemplate;
    }

    public Object getResult() {
        return result;
    }

}
