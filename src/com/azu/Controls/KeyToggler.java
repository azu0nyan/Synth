package com.azu.Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyToggler implements KeyListener {
    public KeyToggler(Toggleable toggleable, int key) {
        this.key = key;
        this.toggleable = toggleable;
    }

    int key;
    Toggleable toggleable;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(toggleable != null && e.getKeyCode() == key){
            toggleable.toggle();
        }
    }
}
