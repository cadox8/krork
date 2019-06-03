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

import net.athonedevs.krork.api.KrorkAPI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private KrorkAPI API;
    private ArrayList<UIObject> objects;

    /**
     * The constructor of the class
     *
     * @param API The KrorkAPI
     */
    public UIManager(KrorkAPI API) {
        this.API = API;
        objects = new ArrayList<>();
    }

    /**
     * Adds an UIObject
     * @see UIObject
     *
     * @param object The UIObject
     */
    public void addObject(UIObject object) {
        objects.add(object);
    }
    /**
     * Removes an UIObject
     * @see UIObject
     *
     * @param object The UIObject
     */
    public void removeObject(UIObject object) {
        objects.remove(object);
    }

    /**
     * Removes all the objects
     */
    public void removeAllObjects() {
        objects.clear();
    }


    public void tick() {
        objects.stream().filter(UIObject::isEnabled).forEach(UIObject::tick);
    }

    public void render(Graphics g) {
        objects.stream().filter(UIObject::isEnabled).forEach(o -> o.render(g));
    }

    public void onMouseMove(MouseEvent e) {
        objects.forEach(o -> o.onMouseMove(e));
    }

    public void onMouseRelease(MouseEvent e) {
        objects.forEach(o -> o.onMouseRelease(e));
    }

    public ArrayList<UIObject> getObjects() {
        return this.objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }
}
