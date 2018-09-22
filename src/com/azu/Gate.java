package com.azu;

public class Gate implements SoundSource {

    private boolean closed = false;
    SoundSource source;

    public Gate(SoundSource source) {
        this.source = source;
    }

    @Override
    public double getSoundSample() {
        if(closed || source == null) {
            return 0;
        }
        return source.getSoundSample();
    }

    public boolean isClosed() {
        return closed;
    }
    public void close(){
        closed = true;
    }
    public void open(){
        closed = false;
    }
}
