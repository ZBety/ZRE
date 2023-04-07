package com.example.ruleEngine.domain.actor.node.template;

import com.example.ruleEngine.domain.Coupons;
import com.example.ruleEngine.domain.ModelProperty;
import com.example.ruleEngine.domain.NodeTemplate;
import com.example.ruleEngine.domain.Template;
import com.example.ruleEngine.service.Action;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Template(name="CouponsRule", Type = "COUPONS_RULE")
public class CouponsRuleTemplate implements NodeTemplate {

    @ModelProperty(component = "Checkbox" , defaultValue = "GRANT_COUPONS,SEND_EMAIL,ADD_POINTS", defaultType = "List")
    private List<ActionEnum> actions;

    @ModelProperty(component = "Checkbox", defaultValue = "满500减200,满600减300",defaultType = "List")
    private List<String> coupons;

    enum ActionEnum {
        GRANT_COUPONS("GRANT_COUPONS"),
        SEND_EMAIL("SEND_EMAIL"),
        ADD_POINTS("ADD_POINTS");

        public final String value;
        ActionEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public void action(String username, List<String> coupons, Action action) {
        actions.forEach((item -> {
            switch (item) {
                case GRANT_COUPONS -> action.grantCoupons(username, coupons);
                case SEND_EMAIL -> action.sendEmail(username);
                case ADD_POINTS -> action.addPoints(username);
                default -> System.out.println("未知的行为！");
            }
        }));
    }
}
