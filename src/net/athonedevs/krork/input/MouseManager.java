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
import net.athonedevs.krork.ui.UIManager;

import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

    @Getter private boolean leftPressed, rightPressed;
    private int mouseX, mouseY, mouseXClick, mouseYClick;
    @Getter @Setter private UIManager uiManager;

    //Mouse
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) leftPressed = true;
        if (e.getButton() == MouseEvent.BUTTON3) rightPressed = true;

        mouseXClick = e.getX();
        mouseYClick = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) leftPressed = false;
        if (e.getButton() == MouseEvent.BUTTON3) rightPressed = false;

        if (uiManager != null) uiManager.onMouseRelease(e);

        mouseXClick = 0;
        mouseYClick = 0;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if (uiManager != null) uiManager.onMouseMove(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    //Wheel
    public void mouseWheelMoved(MouseWheelEvent e) {
    }
}
