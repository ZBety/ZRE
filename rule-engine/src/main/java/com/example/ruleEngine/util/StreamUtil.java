package com.example.ruleEngine.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StreamUtil {

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
