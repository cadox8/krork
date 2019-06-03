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

package net.athonedevs.krork.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;

    protected boolean hovering = false;
    protected boolean enabled = true;

    protected Font font = new Font("Arial", Font.PLAIN, 11);

    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        hovering = bounds.contains(e.getX(), e.getY());
    }

    public void onMouseRelease(MouseEvent e) {
        if (hovering) onClick();
    }

    public void setSize(int size) {
        setStyleAndSize(getFont().getStyle(), size);
    }
    public void setStyle(int style) {
        setStyleAndSize(style, getFont().getSize());
    }
    public void setStyleAndSize(int style, int size) {
        setFont(getFont().deriveFont(style, size));
    }

    public void newFont(String font, int style, int size) {
        setFont(new Font(font, style, size));
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public boolean isHovering() {
        return this.hovering;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public Font getFont() {
        return this.font;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
