package com.azu.Controls;

import com.azu.Gate;
import com.azu.SoundSource;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GateControlledWithKey extends Gate implements KeyListener {
    public GateControlledWithKey(SoundSource source, int key) {
        super(source);
        this.key = key;
    }

    public GateControlledWithKey(SoundSource source) {
        super(source);
    }

    public boolean muteWhenPressed = false;
    public int key = KeyEvent.VK_SPACE;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == key){
            if(muteWhenPressed){
                close();
            } else {
                open();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == key){
            if(!muteWhenPressed){
                close();
            } else {
                open();
            }
        }
    }
}
