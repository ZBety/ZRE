package com.example.ruleEngine.util;

import com.example.ruleEngine.domain.NodeDefinitionInfo;
import com.example.ruleEngine.domain.Template;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScannerUtil {

    private static String packageName = "com.example.ruleEngine.domain.actor.node.template";

    public static List<NodeDefinitionInfo> findAllTemplate() {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(Template.class);
        return types.stream().map(NodeModelDescriptor::describe)
                .collect(Collectors.toList());
    }

    public static List<String> findAllType() {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(Template.class);
        return types.stream()
                .map((item) -> item.getAnnotation(Template.class).Type())
                .collect(Collectors.toList());
    }
}
