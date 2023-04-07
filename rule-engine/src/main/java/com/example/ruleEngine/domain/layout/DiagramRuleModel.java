package com.example.ruleEngine.domain.layout;

import lombok.Data;
import org.sdk.DataModel;
import org.sdk.RuleModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class DiagramRuleModel implements RuleModel, DataModel {

    @Id
    private String id;

    private String no;

    private String name;

    private String type = "DIAGRAM";

    private String version = "v2";

    private int reversion = 1;

    private String workspaceId;

    private String description;

    private Boolean disabled = false;

    private long samplingInterval = 0;

    private List<EndpointModel> inputs = new ArrayList<>();

    private List<EndpointModel> outputs = new ArrayList<>();

    private List<NodeRuleModel> nodes = new ArrayList<>();

    private List<Edge> edges = new ArrayList<>();
}
