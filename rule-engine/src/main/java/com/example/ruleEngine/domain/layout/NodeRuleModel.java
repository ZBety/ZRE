package com.example.ruleEngine.domain.layout;

import com.example.ruleEngine.domain.AttributeDescribe;
import com.example.ruleEngine.domain.DataModel;
import com.example.ruleEngine.domain.Position;
import com.example.ruleEngine.util.ObjectUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class NodeRuleModel implements RuleModel {
    public String id;
    public String name;
    public String type;
    public String description;
    public Boolean disabled;
    public Position position;
    public Position positionAbsolute;
    public List<AttributeDescribe> properties;
    public Map<String, Object> layout;
//    public DiagramPlugin plugins;
    public List<EndpointModel> inputs = new ArrayList<>();
    public List<EndpointModel> outputs = new ArrayList<>();


    public <T extends DataModel> T getNodeTemplate(Class<T> clazz) {
        return ObjectUtil.convert(this.layout, clazz);
    }

    public void propToLayout() {
        this.layout = this.properties.stream()
                .collect(Collectors.toMap(AttributeDescribe::getName,AttributeDescribe::getValue));
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