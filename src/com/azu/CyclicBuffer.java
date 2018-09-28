package com.azu;

public class CyclicBuffer {
    int size;
    double buffer [];
    SoundSource source;
    public CyclicBuffer(SoundSource source, int size) {
        this.source = source;
        this.size = size;
        buffer = new double[size];
    }

    public void updateBuffer(){
        if(source == null)return;
        buffer[Core.getSampleNumber() % size] = source.getSoundSample();
    }

    public  double getFromBuffer(int offset){
        offset %= size;
        return buffer[(Core.getSampleNumber() + offset + size) % size];
    }
    public  double [] getBuffer(){
        return buffer;
    }


    public double getSize() {
        return size;
    }
}
