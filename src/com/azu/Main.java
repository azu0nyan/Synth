package com.azu;

import com.azu.Controls.SquareMouseController;
import com.azu.Controls.GateControlledWithKey;
import com.azu.Controls.KeyToggler;
import ru.ege.engine.EGEngine;

import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class Main {




    public static void main(String[] args) throws LineUnavailableException {
        EGEngine.instance().dispose();
        EGEngine.instance().setUndecorated(true);
        EGEngine.instance().setVisible(true);
        EGEngine.instance().startDrawingThread();


        testMouse2();
        Player.play();

    }

    private static void initTestCombinator() {
        List<GateControlledWithKey> list = Arrays.asList(
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("C3").freq), KeyEvent.VK_Z),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("DB3").freq), KeyEvent.VK_S),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("D3").freq), KeyEvent.VK_X),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("EB3").freq), KeyEvent.VK_D),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("E3").freq), KeyEvent.VK_C),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("F3").freq), KeyEvent.VK_V),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("GB3").freq), KeyEvent.VK_G),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("G3").freq), KeyEvent.VK_B),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("AB3").freq), KeyEvent.VK_H),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("A3").freq), KeyEvent.VK_N),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("BB3").freq), KeyEvent.VK_J),
                new GateControlledWithKey(new Oscillator(Oscillator.saw, Note.getNoteByName("B3").freq), KeyEvent.VK_M),
                
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("C4").freq), KeyEvent.VK_Q),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("DB4").freq), KeyEvent.VK_2),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("D4").freq), KeyEvent.VK_W),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("EB4").freq), KeyEvent.VK_3),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("E4").freq), KeyEvent.VK_E),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("F4").freq), KeyEvent.VK_R),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("GB4").freq), KeyEvent.VK_5),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("G4").freq), KeyEvent.VK_T),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("AB4").freq), KeyEvent.VK_6),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("A4").freq), KeyEvent.VK_Y),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("BB4").freq), KeyEvent.VK_7),
                new GateControlledWithKey(new Oscillator(Note.getNoteByName("B4").freq), KeyEvent.VK_U));
        list.stream().forEach(x->{
            EGEngine.i().addKeyListener(x);
            x.close();
        });
        Combinator combinator = new Combinator(list);
        /*Delay delay = new Delay(combinator, 1d);
        Player.soundSource = new Combinator(delay,combinator);*/
       Player.soundSource = combinator;
    }

    public static void testMouse(){
        Note note = Note.getNoteByName("A4");
        Oscillator source = new Oscillator(Oscillator.sine, note.freq);
        Oscillator source2 = new Oscillator(Oscillator.sine, note.freq);
        SquareMouseController controller = new SquareMouseController(source,Note.getNoteByName("C2").freq, Note.getNoteByName("C4").freq, source2,Note.getNoteByName("C2").freq, Note.getNoteByName("C4").freq);
        controller.left = (controller.sizeX - controller.sizeY )/2;
        controller.sizeX = controller.sizeY;
        EGEngine.i().addDrawableObject(controller);
        Combinator sum = new Combinator(source, source2);

        Player.soundSource = sum;
    }
    public static void testMouse2(){
        Note note = Note.getNoteByName("A4");
        Oscillator source = new Oscillator(Oscillator.sine, note.freq);
        //SimpleLowPassFilter filter =  new SimpleLowPassFilter(source2);
        //  Player.soundSource = volume;
        /*Delay delay = new Delay(volume, 0.5d);
        Delay delay2 = new Delay(delay, 0.5d);
        Delay delay3 = new Delay(delay2, 0.5d);
        Delay delay4 = new Delay(delay3, 0.5d);*/
        // Combinator sum = new Combinator(delay, delay2,delay3, delay4, volume);
        BesselFilter besselFilter = new BesselFilter(source);
        VolumeControl volume = new VolumeControl(besselFilter);
        EGEngine.i().addKeyListener(new KeyToggler(besselFilter, KeyEvent.VK_SPACE));
        Player.soundSource = volume;
        //SquareMouseController controller = new SquareMouseController(source,Note.getNoteByName("C2").freq, Note.getNoteByName("C5").freq, volume,0, 4);
        SquareMouseController controller = new SquareMouseController(source,Note.getNoteByName("C2").freq, Note.getNoteByName("C5").freq, besselFilter,Note.getNoteByName("C2").freq, Note.getNoteByName("C5").freq);
        controller.left = (controller.sizeX - controller.sizeY )/2;
        controller.sizeX = controller.sizeY;
        EGEngine.i().addDrawableObject(controller);
    }

}
