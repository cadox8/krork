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

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.api.KrorkAPI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private KrorkAPI API;
    @Getter @Setter private ArrayList<UIObject> objects;

    public UIManager(KrorkAPI API) {
        this.API = API;
        objects = new ArrayList<>();
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

    public void addObject(UIObject o) {
        objects.add(o);
    }
    public void removeObject(UIObject o) {
        objects.remove(o);
    }
    public void removeAllObjects() {
        objects.clear();
    }
}
