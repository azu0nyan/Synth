package com.azu.Controls;

import com.azu.Note;
import com.azu.Oscillator;
import ru.ege.engine.DrawableObject;
import ru.ege.engine.EGEngine;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class OscillatorFreqMouseController implements DrawableObject {

    double minFreq = Note.getNoteByName("A2").freq;
    double maxFreq = Note.getNoteByName("A6").freq;
    boolean horizontal = true;
    //area
    int left = 0;
    int top = 0;
    int sizeX = EGEngine.i().getWidth();
    int sizeY = EGEngine.i().getHeight();

    Oscillator oscillator;

    public OscillatorFreqMouseController(Oscillator oscillator) {
        this.oscillator = oscillator;
    }

    @Override
    public void drawAndUpdate(Graphics2D graphics2D, double v) {
        Point mouse = EGEngine.i().getMousePosition();
        if(mouse != null){
            //in area
            if(mouse.getX() > left && mouse.getX() < left + sizeX && mouse.getY() > top && mouse.getY() < left + sizeY ){
                double xOffset = mouse.getX() - left;
                double yOffset = mouse.getY() - top;

                double xFraction = xOffset / sizeX;
                double yFraction = yOffset / sizeY;

                double freqDelta = maxFreq - minFreq;
                double newFreq = minFreq;
                if(horizontal){
                    newFreq += freqDelta * xFraction;
                } else {
                    newFreq += freqDelta * yFraction;
                }
                if(oscillator != null){
                    oscillator.setFreq(newFreq);
                }

            }
        }
    }
}
