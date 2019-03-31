/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 *
 */

package net.athonedevs.krork.input;

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.ui.UIField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyManager implements KeyListener {

    private KrorkAPI API;

    private boolean[] keys, justPressed, cantPress;

    @Getter @Setter private UIField writingTo;

    public KeyManager(KrorkAPI API) {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];

        this.API = API;

        writingTo = null;
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
        if (writingTo != null) {
            if (e.getKeyChar() == 8) {
                if (writingTo.getText().toCharArray().length <= 0) return;
                writingTo.setText(String.valueOf(Arrays.copyOfRange(writingTo.getText().toCharArray(), 0, writingTo.getText().toCharArray().length - 1)));
                return;
            }
            writingTo.setText(writingTo.getText() + e.getKeyChar());
        }
    }
}
