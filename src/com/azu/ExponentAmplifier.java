package com.azu;

import com.azu.Controls.ControllableValue;

public class ExponentAmplifier implements SoundSource, ControllableValue {
    SoundSource source;
    private double value;

    public ExponentAmplifier(SoundSource source) {
        this.source = source;
    }

    @Override
    public double getSoundSample() {
        double sample = source.getSoundSample();
        return Math.tanh(Math.signum(sample) * Math.pow(Math.abs(sample), value)) * Core.getMaxVolume();
    }

    @Override
    public void set(double value) {

        this.value = value;
    }
}
