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

package net.athonedevs.krork.nysvaui;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.helpers.RelativeDimension;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class NysvaUI {

    private final long componentID;

    protected final KrorkAPI api;

    protected RelativeDimension relativeDimension;

    private boolean draggable = false;
    protected boolean hovering = false;
    protected boolean enabled = true;

    protected Font font = KrorkAPI.getGameFont();

    protected int maxWidth = -1;

    private NysvaUI parent;
    protected List<NysvaUI> components;

    /**
     * Default NysvaUI constructor
     */
    public NysvaUI(KrorkAPI api) {
        componentID = new Random().nextLong();
        this.api = api;

        components = new ArrayList<>();
        setRelativeDimension(new RelativeDimension());
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        hovering = getRelativeDimension().getBounds().contains(e.getX(), e.getY());
    }

    // ToDo: Make real & smooth movement
    public void onMouseDragged(MouseEvent e) {
        if (hovering && isDraggable()) {
            getRelativeDimension().setX(api.getMouseManager().getMouseX() - (getRelativeDimension().getWidth() / 2));
            getRelativeDimension().setY(api.getMouseManager().getMouseY() - 20);
        }
        if (!components.isEmpty()) components.forEach(c -> {
            final int newX = e.getX() > c.getRelativeDimension().getX() ? c.getRelativeDimension().getX() + (e.getX() - c.getRelativeDimension().getX()) : c.getRelativeDimension().getX() - (e.getX() + c.getRelativeDimension().getX());
            final int newY = e.getY() > c.getRelativeDimension().getY() ? c.getRelativeDimension().getY() + (e.getY() - c.getRelativeDimension().getY()) : c.getRelativeDimension().getY() - (e.getY() + c.getRelativeDimension().getY());

            c.getRelativeDimension().setX(newX - (getRelativeDimension().getWidth() / 2) + 5);
            c.getRelativeDimension().setY(newY + 20 + 5);
        });
    }

    public void onMouseClicked(MouseEvent e) {
        if (hovering) onClick();
    }


    public RelativeDimension getRelativeDimension() {
        return relativeDimension == null ? relativeDimension = new RelativeDimension() : relativeDimension;
    }
    public void setRelativeDimension(RelativeDimension relativeDimension) {
        this.relativeDimension = relativeDimension;
        setMaxWidth(relativeDimension.getMaxWidth());
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public boolean isHovering() {
        return this.hovering;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Font getFont() {
        return this.font;
    }
    public void setFont(Font font) {
        this.font = font;
    }
    public void newFont(String font, int style, int size) {
        setFont(new Font(font, style, size));
    }
    public void customizeFont(int style, int size) {
        setFont(getFont().deriveFont(style, size));
    }

    public NysvaUI getParent() {
        return parent;
    }

    public void setParent(NysvaUI parent) {
        this.parent = parent;
    }

    public long getComponentID() {
        return componentID;
    }
}
