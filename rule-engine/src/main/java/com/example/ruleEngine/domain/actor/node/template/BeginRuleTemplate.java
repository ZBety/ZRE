package com.example.ruleEngine.domain.actor.node.template;

import com.example.ruleEngine.domain.DataMsgCheck;
import com.example.ruleEngine.domain.ModelProperty;
import com.example.ruleEngine.domain.NodeTemplate;
import com.example.ruleEngine.domain.Template;
import lombok.Data;

import java.util.List;

@Data
@Template(name="BeginRule", Type = "BEGIN_RULE")
public class BeginRuleTemplate implements NodeTemplate {

    private String topic;

    @ModelProperty( component = "List")
    private List<DataMsgCheck> parameters;

    public BeginRuleTemplate() {
    }

    public BeginRuleTemplate(String topic, List<DataMsgCheck> parameters) {
        this.topic = topic;
        this.parameters = parameters;
    }
}
