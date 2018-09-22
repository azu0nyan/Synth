package com.azu;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import static com.azu.Core.*;

public class Player {
    static SoundSource soundSource;
    static int bufferSize = SAMPLE_RATE / 16;
    static boolean stop = false;

    public static void play() {

        try {
            final AudioFormat af = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, 1, true, true);
            SourceDataLine line = AudioSystem.getSourceDataLine(af);
            line.open(af, bufferSize);
            line.start();

            while (!stop) {
                Core.sampleNumber++;
                double value = soundSource.getSoundSample();
                byte valueB = (byte) value;
                line.write(new byte[]{valueB}, 0, 1);
            }


            line.drain();
            line.close();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }


    }
}
