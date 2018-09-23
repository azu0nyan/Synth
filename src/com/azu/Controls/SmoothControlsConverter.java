package com.azu.Controls;


import com.azu.Utils;

import java.util.function.Consumer;

public class SmoothControlsConverter implements ControllableValue{
    public enum Mode {CLAMP, CYCLE}

    public double min = 0;
    public double max = 1;
    Mode mode = Mode.CLAMP;
    ControllableValue controllable;

    public SmoothControlsConverter(double min, double max, Mode mode, ControllableValue controllable) {
        this.min = min;
        this.max = max;
        this.mode = mode;
        this.controllable = controllable;
    }

    public SmoothControlsConverter(double min, double max, ControllableValue controllable) {
        this.min = min;
        this.max = max;
        this.controllable = controllable;
    }

    public void set(double value) {
        switch (mode) {
            case CLAMP:
                value = Utils.clamp(0, 1, value);
                break;
            case CYCLE:
                if (value > 0) {
                    value = value - Math.floor(value);
                } else {
                    value = value + Math.abs(Math.floor(value));
                }
        }
        double converted = value * (max - min) + min;
        if(controllable != null){
            controllable.set(converted);
        }

    }
}



