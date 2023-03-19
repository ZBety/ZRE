package com.example.ruleEngine.util;

import com.example.ruleEngine.domain.AttributeDescribe;
import com.example.ruleEngine.domain.ModelProperty;
import com.example.ruleEngine.domain.NodeDefinitionInfo;
import com.example.ruleEngine.domain.Template;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            Optional.ofNullable(modelProperty)
                            .map(ModelProperty::defaultValue)
                            .ifPresent(attribute::setValue);
            attribute.setDescription(null);
            attributes.add(attribute);
        }
        return attributes;
    }
}
