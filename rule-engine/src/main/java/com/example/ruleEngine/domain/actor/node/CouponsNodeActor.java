package com.example.ruleEngine.domain.actor.node;

import com.example.ruleEngine.domain.Coupons;
import com.example.ruleEngine.domain.actor.NodeActor;
import com.example.ruleEngine.domain.actor.node.template.CouponsRuleTemplate;
import com.example.ruleEngine.domain.actor.node.template.EndRuleTemplate;
import com.example.ruleEngine.domain.io.OutputSlot;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.NodeRuleModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;
import com.example.ruleEngine.service.Action;

import java.util.HashMap;
import java.util.List;

public class CouponsNodeActor extends NodeActor<EndRuleTemplate, NodeRuleModel> {
    private final CouponsRuleTemplate nodeTemplate;

    public static final String NAME = "COUPONS_RULE";


    private Object result;

    public CouponsNodeActor(RuleEngineContext ctx, DiagramRuleModel diagramRuleModel, NodeRuleModel nodeRuleModel) {
        super(ctx, diagramRuleModel, nodeRuleModel);
        this.nodeTemplate = nodeRuleModel.getNodeTemplate(CouponsRuleTemplate.class);
    }

    @Override
    protected void onHandle(DataMsg dataMsg) {
        HashMap<String, Object> map = dataMsg.dataAsMap();

        double amount = Double.parseDouble(map.get("amount").toString());
        String username = map.get("username").toString();
        this.nodeTemplate.action(username, this.nodeTemplate.getCoupons(), this.getAction());
        OutputSlot<DataMsg> outputs = getOutputs();
        outputs.send(dataMsg);
    }

    public CouponsRuleTemplate getNodeTemplate() {
        return nodeTemplate;
    }

    public Object getResult() {
        return result;
    }
}
