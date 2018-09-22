package com.azu;

public class Utils {
    public static double clamp(double min, double max, double value){
        return Math.min(max, Math.max(min, value));
    }
}
