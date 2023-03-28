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
        if (this.layout == null){
            propToLayout();
        }
        return ObjectUtil.convert(this.layout, clazz);
    }

    public void propToLayout() {
        this.layout = this.properties.stream()
                .collect(Collectors.toMap(AttributeDescribe::getName,AttributeDescribe::getValue));
    }

}