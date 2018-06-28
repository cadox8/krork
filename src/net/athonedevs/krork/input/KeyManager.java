package net.athonedevs.krork.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;

    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public abstract void tick();

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) return false;
        return justPressed[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) return;
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) return;
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
