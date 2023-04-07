package com.example.ruleEngine.domain.actor.node;

import com.example.ruleEngine.domain.DataMsgCheck;
import com.example.ruleEngine.domain.actor.NodeActor;
import com.example.ruleEngine.domain.actor.node.template.AgeDiscountRuleTemplate;
import com.example.ruleEngine.domain.actor.node.template.BeginRuleTemplate;
import com.example.ruleEngine.domain.io.OutputSlot;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.NodeRuleModel;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        HashMap<String, Object> map = dataMsg.dataAsMap();
        List<DataMsgCheck> checks = this.nodeTemplate.getParameters();
        /**
         * 验证DataMsg数据是否合法
         */
        if(this.nodeTemplate.getTopic().equals(map.get("topic"))) {
            if (checks.stream().allMatch(check -> map.containsKey(check.getName()))) {
                OutputSlot<DataMsg> outputs = getOutputs();
                outputs.send(dataMsg);
            } else {
                System.out.println("数据校验失败，匹配失败，拒绝执行！  数据："+map.toString());
            }
        }else {
            System.out.println("匹配失败！");
        }

    }

    public BeginRuleTemplate getNodeTemplate() {
        return nodeTemplate;
    }

    public Object getResult() {
        return result;
    }

}
