package com.example.ruleEngine.domain.actor.node.template;

import lombok.Data;
import org.sdk.DataModel;
import org.sdk.Template;
import org.sdk.template.NodeTemplate;

@Data
@Template(name="AgeDiscountRule", Type = "AGE_DISCOUNT_RULE")
public class AgeDiscountRuleTemplate implements NodeTemplate, DataModel {

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
