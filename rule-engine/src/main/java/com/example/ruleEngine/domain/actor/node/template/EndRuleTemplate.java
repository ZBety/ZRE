package com.example.ruleEngine.domain.actor.node.template;

import lombok.Data;
import org.sdk.Template;
import org.sdk.template.NodeTemplate;

@Data
@Template(name="EndRule", Type = "END_RULE")
public class EndRuleTemplate implements NodeTemplate {

}
