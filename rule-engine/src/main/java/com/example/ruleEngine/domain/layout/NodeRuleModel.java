package com.example.ruleEngine.domain.layout;

import com.example.ruleEngine.domain.AttributeDescribe;
import com.example.ruleEngine.domain.Position;
import lombok.Data;
import org.sdk.DataModel;
import org.sdk.RuleModel;
import org.sdk.util.ObjectUtil;

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
        //有bug，未对节点中，属性对空值进行处理，复现方式：开始节点中不设置数据结构
        this.layout = this.properties.stream()
                .collect(Collectors.toMap(AttributeDescribe::getName,AttributeDescribe::getValue));
    }

}