package com.azu;

import com.azu.Controls.ControllableValue;
import com.azu.Controls.Toggleable;

public class Filter implements SoundSource, Toggleable, ControllableValue {
    private double fc;
    private double qFactor;
    private double g;
    SoundSource source;


    public Filter(SoundSource source, double fc, double qFactor, double g) {
        this.source = source;
        setConsts(fc, qFactor, g);
    }


    void setConsts(double fc, double qFactor, double g) {
        this.fc = fc;
        this.qFactor = qFactor;
        this.g = g;
    }


    double lastXs[] = {1, 1};
    double lastY = 1;

    @Override
    public double getSoundSample() {
        final int fs = Core.SAMPLE_RATE;

        double A = Math.sqrt(Math.pow(10, g / 20.0));
        double wc = 2 * Math.PI * fc / fs;
        double wS = Math.sin(wc);
        double wC = Math.cos(wc);
        double a = wS / (2 * qFactor);
        double b = Math.sqrt(A) / qFactor;

        double b0 = (1 - wC) / 2;
        double b1 = (1 - wC);
        double b2 = (1 - wC) / 2;
        double a0 = 1 + a;
        double a1 = -2 * wC;
        double a2 = 1 - a;


        double xn = source.getSoundSample();
        double xn1 = lastXs[1];
        double xn2 = lastXs[0];
        double yn1 = lastY;

        double yn = (b0 / a0) * xn + (b1 / a0) * xn1 + (b2 / a0) * xn2 - (a1 / a0) * yn1 - (a2 / a0) * yn1;
        lastY = yn;
        lastXs = new double[]{xn1, xn };


        return yn;
    }

    /* public double h(double s, int n, double freq){
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

 */
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
        // removeFreq = value;
    }
}
