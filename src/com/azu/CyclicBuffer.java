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

    public void updateBuffer(double val){
        buffer[Core.getSampleNumber() % size] = val;
    }

    public void updateBufferFromSource(){
        if(source == null)return;
        updateBuffer(source.getSoundSample());

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
