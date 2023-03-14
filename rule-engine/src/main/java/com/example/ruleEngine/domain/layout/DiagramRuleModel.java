package com.example.ruleEngine.domain.layout;

import com.example.ruleEngine.domain.DataModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class DiagramRuleModel implements RuleModel, DataModel {

    @Id
    String id;

    String no;

    String name;

    String type = "DIAGRAM";

    String version = "v2";

    int reversion = 1;

    String workspaceId;

    String description;

    Boolean disabled = false;

    long samplingInterval = 0;

    List<EndpointModel> inputs = new ArrayList<>();

    List<EndpointModel> outputs = new ArrayList<>();

    List<NodeRuleModel> nodes = new ArrayList<>();
//
//    List<PipeModel> pipes = new ArrayList<>();
}
