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

package net.athonedevs.krork.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.creatures.Creature;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    @Getter @Setter private KrorkAPI API;

    @Getter @Setter private ArrayList<Entity> entities;

    private Comparator<Entity> renderSorter = (Entity a, Entity b)-> {
        if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) return -1;
        return 0;
    };

    public EntityManager(KrorkAPI API) {
        this.API = API;
        entities = new ArrayList<>();
    }

    public void tick() {
        final Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            final Entity e = it.next();
            e.tick();
            e.fixAnimations();
            if (!e.isActive()) it.remove();
        }
        entities.sort(renderSorter);
    }

    public synchronized void render(Graphics g) {
        entities.forEach(e -> e.specialRender(g));
        entities.forEach(e -> e.render(g));
    }

    public synchronized void addEntity(@NonNull Entity e) {
        entities.add(e);
    }

    public Entity getEntityOnLoc(float x, float y) {
        return API.getWorld().getEntityManager().getEntities().stream().filter(e -> e.getBounds().contains(new Point((int)x, (int)y))).findAny().orElse(null);
    }

    public void freezeCreatures(Creature except) {
        entities.stream().filter(e -> e instanceof Creature).filter(e -> !e.equals(except)).forEach(e -> ((Creature)e).setFreeze(true));
    }

    public void killAll() {
        entities.forEach(Entity::kill);
    }
}
