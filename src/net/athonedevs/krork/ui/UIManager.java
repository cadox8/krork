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
        objects.forEach(UIObject::tick);
    }

    public void render(Graphics g) {
        objects.forEach(o -> o.render(g));
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
}
