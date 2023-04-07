package com.example.ruleEngine.plugin.management.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class PluginManifest {

    @Id
    private String Id;
}
