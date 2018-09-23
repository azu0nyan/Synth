package com.azu;

import com.azu.Controls.ControllableValue;

import java.util.function.Function;

import static com.azu.Core.*;

public class Oscillator implements SoundSource, ControllableValue {

    public static Function<Double, Double> square = x -> Math.signum((x - Math.floor(x)) * 2 - 1);
    public static Function<Double, Double> saw = x -> ((x - Math.floor(x)) * 2 - 1) /2;// /2 - normalized
    public static Function<Double, Double> sine = x -> Math.sin((x - Math.floor(x)) * 2.0 * Math.PI);


    double freq = 440;
    double timeDelta = 0;
    Function<Double, Double> function;

    public Oscillator(double freq) {
        function = sine;
        this.freq = freq;
    }

    public Oscillator(Function<Double, Double> function) {
        this.function = function;
    }

    public Oscillator(Function<Double, Double> function, double freq) {
        this.freq = freq;
        this.function = function;
    }

    @Override
    public double getSoundSample() {
        double t = getTime() + timeDelta;

        double arg = t * freq;
        double res = getMaxVolume() * function.apply(arg);

        return res;
    }

    public void setFreq(double newFreq) {
        timeDelta = ((getTime() +  timeDelta) * freq  - getTime() *newFreq)/ newFreq ;
       // System.out.println(timeDelta);
        freq = newFreq;
    }

    @Override
    public void set(double value) {
        setFreq(value);
    }
}
