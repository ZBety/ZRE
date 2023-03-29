package com.example.ruleEngine.domain.io;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class OutputSlot<T> {

    private List<InputSlot<T>> inputs = new ArrayList<>();

    public OutputSlot() {}

    public void connect(InputSlot<T> input) {
        inputs.add(input);
    }

    public void send(T data) {
        inputs.forEach(input -> input.receive(data));
    }

    public void send(Supplier<T> dataSupplier) {
        inputs.forEach(input -> input.receive(dataSupplier.get()));
    }
}
