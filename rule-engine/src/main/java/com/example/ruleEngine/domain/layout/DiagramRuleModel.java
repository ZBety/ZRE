package com.example.ruleEngine.domain.layout;

import com.example.ruleEngine.domain.rules.DiscountRule;
import lombok.Data;

@Data
public class DiagramRuleModel implements RuleModel, DiscountRule {
    private String id;

    private String name;

    private int discountAge;

    private double discountRate;

    public DiagramRuleModel(String id, String name, int discountAge, double discountRate) {
        this.id = id;
        this.name = name;
        this.discountAge = discountAge;
        this.discountRate = discountRate;
    }

    @Override
    public double calculateDiscount(int num) {
        return num > discountAge ? discountRate : 0;
    }
}
