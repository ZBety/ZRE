package com.example.ruleEngine.domain.actor.node.template;

import com.example.ruleEngine.domain.NodeTemplate;
import com.example.ruleEngine.domain.Template;
import lombok.Data;

@Data
@Template(name="EndRule", Type = "END_RULE")
public class EndRuleTemplate implements NodeTemplate {

}
