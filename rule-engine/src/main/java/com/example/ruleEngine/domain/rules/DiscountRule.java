package com.example.ruleEngine.domain.rules;


import com.example.ruleEngine.domain.rules.Rule;

/**
 * 折扣规则
 */

public interface DiscountRule extends Rule {

    double calculateDiscount(int num);

}
