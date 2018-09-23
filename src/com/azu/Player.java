package com.azu;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import java.nio.ByteBuffer;

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
                if (Core.SAMPLE_SIZE_IN_BITS == 8) {
                    byte valueB = (byte) value;
                    line.write(new byte[]{valueB}, 0, 1);
                }
                if(Core.SAMPLE_SIZE_IN_BITS == 16) {
                    int toWrite = (int) value;
                    line.write(new byte[]{(byte) ((toWrite & 0x0000FF00) >> 8), (byte) ((toWrite & 0x000000FF) >> 0)}, 0, 2);
                }
            }


            line.drain();
            line.close();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }


    }
}
