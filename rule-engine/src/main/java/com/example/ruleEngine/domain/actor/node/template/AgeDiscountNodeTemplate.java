package com.example.ruleEngine.domain.actor.node.template;

public class AgeDiscountNodeTemplate {

    private String id;

    private String name;

    private int discountAge;

    private double discountRate;

    private String type = "AGE_DISCOUNT_RULE";

    public AgeDiscountNodeTemplate() {
    }

    public AgeDiscountNodeTemplate(String id, String name, int discountAge, double discountRate) {
        this.discountAge = discountAge;
        this.discountRate = discountRate;
    }
}
