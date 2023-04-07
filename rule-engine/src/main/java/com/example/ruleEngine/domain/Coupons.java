package com.example.ruleEngine.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Coupons {
    private String name;
    private String type;
    private double amount;
    private double amountLevel;
}
