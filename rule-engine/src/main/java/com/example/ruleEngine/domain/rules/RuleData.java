package com.example.ruleEngine.domain.rules;

import com.example.ruleEngine.domain.layout.EndpointModel;
import lombok.Data;
import org.sdk.DataModel;
import org.sdk.RuleModel;
import org.sdk.util.ObjectUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Data
@Document
public class RuleData implements RuleModel {

    @Id
    private String ruleId;

    private HashMap<String, Object> data;

    private String createTime;

    private String version;

    private String type;

    public RuleData(String ruleId, HashMap<String, Object> data, String createTime, String version, String type) {
        this.ruleId = ruleId;
        this.data = data;
        this.createTime = createTime;
        this.version = version;
        this.type = type;
    }

    public <T extends DataModel> T getRuleTemplate(Class<T> clazz) {
        return ObjectUtil.convert(this.data, clazz);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

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
        return this.type;
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

}
