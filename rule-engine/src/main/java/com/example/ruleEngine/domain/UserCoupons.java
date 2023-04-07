package com.example.ruleEngine.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserCoupons {
    private String username;
    private String name;
    private String type;
    private double amount;
    private double amountLevel;
    private Long createTime;
}
