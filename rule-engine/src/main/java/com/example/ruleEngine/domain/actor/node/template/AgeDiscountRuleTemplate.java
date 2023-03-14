package com.example.ruleEngine.domain.actor.node.template;

import com.example.ruleEngine.domain.NodeTemplate;
import lombok.Data;

@Data
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
        return num > discountAge ? discountRate : 0;
    }

}
