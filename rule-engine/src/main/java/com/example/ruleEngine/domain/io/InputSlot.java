package com.example.ruleEngine.domain.io;

import java.util.function.Consumer;

public class InputSlot<T> {

    private Consumer<T> consumer;

    public InputSlot() {}

    public void connect(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public void receive(T data) {
        if (consumer != null) {
            consumer.accept(data);
        }
    }
}
