package com.azu;

import com.azu.Controls.ControllableValue;

public class VolumeControl implements SoundSource, ControllableValue {
    SoundSource source;
    double volumeLevel = 1;
    boolean clamp = true;
    boolean tanh = false;
    public VolumeControl(SoundSource source) {
        this.source = source;
    }

    public VolumeControl(SoundSource source, double volumeLevel) {
        this.source = source;
        this.volumeLevel = volumeLevel;
    }

    @Override
    public double getSoundSample() {
        if(source == null){
            return 0;
        }
        if(tanh){
            return Math.tanh( volumeLevel * source.getSoundSample()/ Core.getMaxVolume()) * Core.getMaxVolume();
        }
        if(!clamp){
            return volumeLevel * source.getSoundSample();
        } else {
            return Utils.clamp(Core.getMinVolume(), Core.getMaxVolume(), volumeLevel * source.getSoundSample());
        }
    }

    @Override
    public void set(double value) {
        volumeLevel = value;
    }
}
