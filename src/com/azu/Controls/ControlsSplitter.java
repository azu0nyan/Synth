package com.azu.Controls;

import java.util.concurrent.CopyOnWriteArrayList;

public class ControlsSplitter implements ControllableValue {
    CopyOnWriteArrayList<ControllableValue> controls = new CopyOnWriteArrayList<>();

    public ControlsSplitter(ControllableValue ... control) {
        for (ControllableValue controllableValue : control) {
            controls.add(controllableValue);
        }
    }

    @Override
    public void set(double value) {
        for (ControllableValue control : controls) {
            control.set(value);
        }
    }
}
