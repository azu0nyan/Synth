package com.azu;

import com.azu.Controls.ControllableValue;
import com.azu.Controls.Toggleable;

import java.awt.event.MouseAdapter;

public class BesselFilter implements SoundSource, Toggleable, ControllableValue {
    int bufferSize = 100;
    CyclicBuffer buffer;
    SoundSource source;



    public BesselFilter(SoundSource source) {
        this.source = source;
        buffer = new CyclicBuffer(source, bufferSize);
    }

    public double h(double s, int n, double freq){
        return theta(0, n) / theta(s / freq, n);
    }
    public double theta(double s, int n){
        double res = 0;
        for(int i = 0; i < n; i++){
            res += a(i, n) * Math.pow(s,i);
        }
        return res;
    }

    public int a(int k, int n){
        return fact(2 * n - k) / ((1<<(n-k + 1) ) * fact(n - k));
    }

    public int fact(int n){
        int res = 1;
        for(int i = 1; i <n ; i++){
            res *= n;
        }
        return res;
    }

    public double calculate(){
        double res = 0;
        for(int i  = 1 ; i <= bufferSize ; i++){
            res  += buffer.getFromBuffer(i) *h(((1d - i / (double)bufferSize ) / (Core.getFrameLength() * bufferSize)), n, removeFreq);
        }
        return res;
    }
    int n = 5;
    double removeFreq = 220;
    public double getSoundSample() {
        buffer.updateBuffer();
        if (source == null) return 0;
        if (on) {
           return  calculate() / Math.PI;
        } else {
            return source.getSoundSample();
        }
    }



    boolean on = true;
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

    @Override
    public void set(double value) {
        removeFreq = value;
    }
}
