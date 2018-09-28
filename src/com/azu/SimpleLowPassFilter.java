package com.azu;

import com.azu.Controls.Toggleable;

public class SimpleLowPassFilter implements SoundSource, Toggleable {

    boolean on = true;

    int bufferSize = 10;
    CyclicBuffer buffer;
    SoundSource source;



    public SimpleLowPassFilter(SoundSource source) {
        this.source = source;
        buffer = new CyclicBuffer(source, bufferSize);
    }

    double a = 0.8f;
    double prevValue = 0;
    @Override
    public double getSoundSample() {
        if (source == null) return 0;
        if (on) {
            double newValue = prevValue + a * (source.getSoundSample() - prevValue);
            prevValue = newValue;
            return newValue;
        } else {
            return source.getSoundSample();
        }
    }

    @Override
    public void toggleOn() {
        on = true;
    }

    @Override
    public void toggleOff() {
        on = false;
    }

    @Override
    public void toggle() {
        on = !on;
        System.out.println("SimpleLowPassFilter:" + (on ? "on" : "off"));
    }
}
