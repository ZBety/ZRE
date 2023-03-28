package com.example.ruleEngine.domain.actor.rules;

import com.example.ruleEngine.domain.actor.node.template.AgeDiscountRuleTemplate;
import com.example.ruleEngine.domain.rules.RuleData;
import com.example.ruleEngine.engine.RuleEngineContext;
import com.example.ruleEngine.msg.DataMsg;

import java.util.HashMap;

public class AgeDiscountRuleActor extends RuleActor<AgeDiscountRuleTemplate, RuleData> {
    private AgeDiscountRuleTemplate ruleTemplate;

    public static final String NAME = "AGE_DISCOUNT_RULE";

    public AgeDiscountRuleActor(RuleEngineContext ctx, RuleData ruleData) {
        super(ctx, ruleData);
        this.ruleTemplate = ruleData.getRuleTemplate(AgeDiscountRuleTemplate.class);
    }

    @Override
    public void onHandle(DataMsg dataMsg) {

    }

    @Override
    public Object execute(DataMsg dataMsg) {
        HashMap<String, Object> map = dataMsg.dataAsMap();
        int age = (int) map.get("age");
        return ruleTemplate.calculateDiscount(age);
    }

    public AgeDiscountRuleTemplate getRuleTemplate() {
        return ruleTemplate;
    }
}
