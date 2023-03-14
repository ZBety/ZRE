package com.example.ruleEngine.domain.layout;

import com.example.ruleEngine.domain.DataModel;
import com.example.ruleEngine.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NodeRuleModel implements RuleModel {
    public String no;
    public String name;
    public String type;
    public String description;
    public Boolean disabled;
    public Map<String, Object> layout;
//    public DiagramPlugin plugins;
    public List<EndpointModel> inputs = new ArrayList<>();
    public List<EndpointModel> outputs = new ArrayList<>();

    @Override
    public String getNo() {
        return null;
    }

    @Override
    public void setNo(String no) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void setType(String type) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public Boolean getDisabled() {
        return null;
    }

    @Override
    public void setDisabled(Boolean disabled) {

    }

    @Override
    public List<EndpointModel> getInputs() {
        return null;
    }

    @Override
    public void setInputs(List<EndpointModel> inputs) {

    }

    @Override
    public List<EndpointModel> getOutputs() {
        return null;
    }

    @Override
    public void setOutputs(List<EndpointModel> outputs) {

    }

    public <T extends DataModel> T getNodeTemplate(Class<T> clazz) {
        return ObjectUtil.convert(this.layout, clazz);
    }
//
//    public static List<ResourceDefinition> allDefinitions() {
//        ResourceDefinitionStorageImpl storage = new ResourceDefinitionStorageImpl();
//        return storage.findAll();
//    }
//
//    public static NodeRuleModel from(DiagramFlowData.Node node) {
//        NodeRuleModel nodeRuleModel = new NodeRuleModel();
//        ResourceDefinition definition = allDefinitions()
//                .stream()
//                .filter(d -> d.type.name.equals(node.nodeType))
//                .findFirst()
//                .get();
//        nodeRuleModel.no = node.id;
//        nodeRuleModel.name = node.stringData("label") != null ? node.stringData("label") : "";
//        nodeRuleModel.type = node.nodeType;
//        nodeRuleModel.layout = new HashMap(node.data);
//        nodeRuleModel.disabled = node.disabled;
//        nodeRuleModel.inputs = definition.inputs.stream().map(i -> new EndpointModel(i.name, i.type)).collect(Collectors.toList());
//        nodeRuleModel.outputs = definition.outputs.stream().map(o -> new EndpointModel(o.name, o.type)).collect(Collectors.toList());
//        return nodeRuleModel;
//    }
}