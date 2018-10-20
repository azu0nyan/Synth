package com.azu;

import com.azu.Controls.ControllableValue;

public class SimpleMorpher implements ControllableValue, SoundSource {
    double firstWeight = 1;

    SoundSource first;
    SoundSource second;

    public SimpleMorpher(SoundSource first, SoundSource second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void set(double value) {
        firstWeight = value;
    }

    @Override
    public double getSoundSample() {
        return first.getSoundSample() * firstWeight + second.getSoundSample() * (1 - firstWeight);
    }
}
