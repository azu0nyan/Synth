package com.azu;

public class Delay implements SoundSource {
    double[] buffer;

    SoundSource source;

    public Delay(SoundSource source, int frames) {
        this.source = source;
        init(frames);
    }

    public Delay(SoundSource source, double time) {
        this.source = source;
        init(Core.getFramesInTime(time));
    }

    void init(int frames) {
        buffer = new double[frames];
    }

    @Override
    public double getSoundSample() {
        if (source != null) {
            buffer[Core.getSampleNumber() % buffer.length] = source.getSoundSample();
        }
        return buffer[(Core.getSampleNumber() + 1) % buffer.length];
    }
}
