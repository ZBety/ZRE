package com.example.ruleEngine.domain.actor.node;

import com.example.ruleEngine.domain.actor.node.template.CouponsRuleTemplate;
import com.example.ruleEngine.domain.actor.node.template.EndRuleTemplate;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.NodeRuleModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.service.Action;
import org.sdk.actor.NodeActor;
import org.sdk.io.OutputSlot;
import org.sdk.msg.DataMsg;

import java.util.HashMap;

public class CouponsNodeActor extends NodeActor<EndRuleTemplate, NodeRuleModel> {
    private final CouponsRuleTemplate nodeTemplate;

    public static final String NAME = "COUPONS_RULE";

    public RuleEngineContext ctx;
    private Object result;

    public CouponsNodeActor(RuleEngineContext ctx, DiagramRuleModel diagramRuleModel, NodeRuleModel nodeRuleModel) {
        super(nodeRuleModel);
        this.ctx = ctx;
        this.nodeTemplate = nodeRuleModel.getNodeTemplate(CouponsRuleTemplate.class);
    }

    @Override
    protected void onHandle(DataMsg dataMsg) {
        HashMap<String, Object> map = dataMsg.dataAsMap();

        double amount = Double.parseDouble(map.get("amount").toString());
        String username = map.get("username").toString();
        this.nodeTemplate.action(username, this.nodeTemplate.getCoupons(), this.ctx.getBean(Action.class));
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
