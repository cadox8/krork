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

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class NysvaManager {

    private final ArrayList<NysvaUI> objects;

    /**
     * The constructor of the class
     */
    public NysvaManager() {
        objects = new ArrayList<>();
    }

    /**
     * Adds an NysvaUI
     * @see NysvaUI
     *
     * @param object The NysvaUI
     */
    public void addObject(NysvaUI object) {
        synchronized (objects) {
            objects.add(object);
        }
    }

    /**
     * Removes an NysvaUI
     * @see NysvaUI
     *
     * @param object The NysvaUI
     */
    public void removeObject(NysvaUI object) {
        removeObject(object.getComponentID());
    }

    /**
     * Removes an NysvaUI
     * @see NysvaUI
     *
     * @param componentID The componentID
     */
    public void removeObject(long componentID) {
        synchronized (objects) {
            final Iterator<NysvaUI> it = objects.iterator();
            while (it.hasNext()) if (it.next().getComponentID() == componentID) it.remove();
        }
    }

    /**
     * Removes all the objects
     */
    public void removeAllObjects() {
        synchronized (objects) {
            final Iterator<NysvaUI> it = objects.iterator();
            while (it.hasNext()) it.remove();
        }
    }

    /**
     * Gets an specific object
     *
     * @param componentID The ID of the component to search
     */
    public NysvaUI getObject(long componentID) {
        return objects.stream().filter(c -> c.getComponentID() == componentID).findAny().orElse(null);
    }

    public void tick() {
        synchronized (objects) {
            try {
                objects.stream().filter(NysvaUI::isEnabled).forEach(NysvaUI::tick);
            } catch (ConcurrentModificationException e) {}
        }
    }

    public void render(Graphics g) {
        synchronized (objects) {
            try {
                objects.stream().filter(NysvaUI::isEnabled).forEach(o -> o.render(g));
            } catch (ConcurrentModificationException e) {}
        }
    }

    public void onMouseDragged(MouseEvent e) {
        objects.forEach(o -> o.onMouseDragged(e));
    }
    public void onMouseMove(MouseEvent e) {
        objects.forEach(o -> o.onMouseMove(e));
    }
    public void onMouseClicked(MouseEvent e) {
        objects.forEach(o -> o.onMouseClicked(e));
    }

    public ArrayList<NysvaUI> getObjects() {
        return this.objects;
    }
}
