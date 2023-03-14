package com.example.ruleEngine.domain.layout;

import java.util.List;

public interface RuleModel {
    String getNo();
    void setNo(String no);

    String getName();
    void setName(String name);

    String getType();
    void setType(String type);

    String getDescription();
    void setDescription(String description);

    Boolean getDisabled();
    void setDisabled(Boolean disabled);

//    DiagramPlugin getPlugins();
//    void setPlugins(DiagramPlugin plugins);

    List<EndpointModel> getInputs();
    void setInputs(List<EndpointModel> inputs);

    List<EndpointModel> getOutputs();
    void setOutputs(List<EndpointModel> outputs);
}
