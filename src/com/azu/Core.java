package com.azu;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Core {

    public static final int SAMPLE_RATE = 16 * 1024;
    public static final int SAMPLE_SIZE_IN_BITS = 16;//only 8 & 16
    public static int  sampleNumber = 0;
    static double getFrameLength(){
        return 1 / (double) Core.SAMPLE_RATE;
    }
    static double getTime(){
        return sampleNumber / (double) Core.SAMPLE_RATE;
    }
    static int getMaxVolume(){
        return 1<<(SAMPLE_SIZE_IN_BITS - 1) -1;// 128 - 1
    }
    static int getMinVolume(){
        return -(1<<(SAMPLE_SIZE_IN_BITS - 1) -1);// 128 - 1
    }



    public static int getFramesInTime(double time) {
        return (int) (time / getFrameLength());
    }
    public static int getSampleNumber(){
        return  sampleNumber;
    }
}
