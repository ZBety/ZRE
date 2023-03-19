package com.example.ruleEngine.domain.actor.node.template;

import com.example.ruleEngine.domain.NodeTemplate;
import com.example.ruleEngine.domain.Rule;
import com.example.ruleEngine.domain.Template;
import lombok.Data;

@Data
@Template(name="AgeDiscountRule", Type = "AGE_DISCOUNT_RULE")
public class AgeDiscountRuleTemplate implements NodeTemplate {

    private String id;

    private String name;

    private int discountAge;

    private double discountRate;

    public AgeDiscountRuleTemplate() {
    }

    public AgeDiscountRuleTemplate(String id, String name, int discountAge, double discountRate) {
        this.discountAge = discountAge;
        this.discountRate = discountRate;
    }

    public double calculateDiscount(int num) {
        return num > discountAge ? discountRate : 1;
    }

}
