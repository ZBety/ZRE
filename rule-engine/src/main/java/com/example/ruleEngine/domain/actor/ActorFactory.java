package com.example.ruleEngine.domain.actor;

import com.example.ruleEngine.domain.actor.diagramRuleActor.DiagramRuleActor;
import com.example.ruleEngine.domain.actor.node.AgeDiscountNodeActor;
import com.example.ruleEngine.domain.actor.node.BeginNodeActor;
import com.example.ruleEngine.domain.actor.node.CouponsNodeActor;
import com.example.ruleEngine.domain.actor.node.EndNodeActor;
import com.example.ruleEngine.domain.actor.rules.AgeDiscountRuleActor;
import com.example.ruleEngine.domain.actor.rules.RuleActor;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.layout.NodeRuleModel;
import com.example.ruleEngine.domain.rules.RuleData;
import com.example.ruleEngine.engine.RuleEngineContext;
import org.sdk.RuleModel;
import org.sdk.actor.NodeActor;

public class ActorFactory {

    /**
     * 构造规则执行器
     */

    public static DiagramRuleActor getDiagramRuleActor(
            RuleEngineContext ctx,
            RuleModel ruleModel
    ) {
        switch (ruleModel.getType()) {
            case "DIAGRAM":
                return new DiagramRuleActor(ctx, (DiagramRuleModel) ruleModel);
            default:
                System.out.println(ruleModel.getType()+"类型的规则模板没有找到！");
                return null;
        }
    }

    public static NodeActor<?,?> getNodeActor(
            RuleEngineContext ctx,
            DiagramRuleModel diagramRuleModel,
            NodeRuleModel nodeRuleModel
    ) {
        NodeActor actor = switch (nodeRuleModel.getType()) {
            case AgeDiscountNodeActor.NAME -> new AgeDiscountNodeActor(ctx, diagramRuleModel, nodeRuleModel);
            case BeginNodeActor.NAME -> new BeginNodeActor(ctx, diagramRuleModel, nodeRuleModel);
            case EndNodeActor.NAME -> new EndNodeActor(ctx, diagramRuleModel, nodeRuleModel);
            case CouponsNodeActor.NAME -> new CouponsNodeActor(ctx, diagramRuleModel, nodeRuleModel);
//            default -> {System.out.println("没有找到" + nodeRuleModel.getType() +"类型的执行器！");
//            null ;}
            default -> null;
        };
        if (actor != null) {
            actor.start();
        }
        return actor;
    }

    public static RuleActor<?,?> getRuleActor(
            RuleEngineContext ctx,
            RuleData ruleData
    ) {
        AgeDiscountRuleActor actor = switch (ruleData.getType()) {
            case AgeDiscountRuleActor.NAME -> new AgeDiscountRuleActor(ctx, ruleData);
            default -> null;
        };
        if (actor != null) {
            actor.start();
        }
        return actor;
    }

}
