package com.example.ruleEngine.util;

import com.example.ruleEngine.domain.AttributeDescribe;
import com.example.ruleEngine.domain.NodeDefinitionInfo;
import org.sdk.ModelProperty;
import org.sdk.Template;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NodeModelDescriptor {

    public static NodeDefinitionInfo describe(Class<?> clazz) {
        List<AttributeDescribe> properties = convertClassToAttributes(clazz);
        Template template = clazz.getAnnotation(Template.class);
        String name = template.name();
        String type = template.Type();
        String description = template.description();
        return new NodeDefinitionInfo(name.toLowerCase(), name, properties, type, description);
    }

    public static List<AttributeDescribe> convertClassToAttributes(Class<?> clazz) {
        List<AttributeDescribe> attributes = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            ModelProperty modelProperty = field.getAnnotation(ModelProperty.class);
            AttributeDescribe attribute = new AttributeDescribe();
            attribute.setName(field.getName());
            attribute.setType(field.getType().getName());
            if (modelProperty != null && !Objects.equals(modelProperty.defaultValue(), "")){
                Object value = modelProperty.defaultValue();
                if (modelProperty.defaultType().equals("List")){
                    value = modelProperty.defaultValue().split(",");
                }
                attribute.setValue(value);
            }
            Optional.ofNullable(modelProperty)
                    .map(ModelProperty::component)
                    .ifPresent(attribute::setComponent);
            attribute.setDescription(null);
            attributes.add(attribute);
        }
        return attributes;
    }
}
