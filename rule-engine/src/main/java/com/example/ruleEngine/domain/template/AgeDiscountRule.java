package com.example.ruleEngine.domain.template;

import com.example.ruleEngine.domain.rules.DiscountRule;

public class AgeDiscountRule implements DiscountRule {

    private String id;

    private String name;

    private int discountAge;

    private double discountRate;

    public AgeDiscountRule(String id, String name, int discountAge, double discountRate) {
        this.discountAge = discountAge;
        this.discountRate = discountRate;
    }

    @Override
    public double calculateDiscount(int num) {
        return num > discountAge ? discountRate : 0;
    }
}
