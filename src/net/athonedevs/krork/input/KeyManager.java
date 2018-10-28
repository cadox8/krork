/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 */

package net.athonedevs.krork.input;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.ex.KeyRegisteredException;
import net.athonedevs.krork.ui.UIField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyManager implements KeyListener {

    private KrorkAPI API;

    private boolean[] keys, justPressed, cantPress;

    private int[] allKeys;

    public KeyManager(KrorkAPI API) {
        keys = new boolean[256];
        allKeys = new int[keys.length];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];

        this.API = API;
    }

    public void tick() {
        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]) {
                cantPress[i] = false;
            } else {
                if (justPressed[i]) {
                    cantPress[i] = true;
                    justPressed[i] = false;
                }
            }
            if (!cantPress[i] && keys[i]) justPressed[i] = true;
        }
    }

    public void registerKeys(int key) throws KeyRegisteredException {
        if (Arrays.asList(allKeys).contains(key)) throw new KeyRegisteredException(key);
        allKeys[key] = key;
    }

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
    public void keyTyped(KeyEvent e) {
        if (API.getMouseManager().getUiManager() != null) API.getMouseManager().getUiManager().getObjects().stream().filter(o -> o instanceof UIField).forEach(o -> ((UIField) o).setText(((UIField) o).getText() + e.getKeyChar()));
    }
}
