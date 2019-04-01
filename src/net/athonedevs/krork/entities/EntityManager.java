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
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            final Entity e = it.next();
            e.tick();
            e.fixAnimations();
            if (!e.isActive()) it.remove();
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g) {
        entities.forEach(e -> e.specialRender(g));
        entities.forEach(e -> e.render(g));
    }

    public void addEntity(@NonNull Entity e) {
/*        try {
            if (exists(e.getEntityID(), e.getEntityName())) throw new EntityRegisteredException(e);
        } catch (EntityRegisteredException ex) {
            Log.log(Log.LogType.DANGER, ex.getMessage());
            ex.printStackTrace();
            return;
        }*/
        entities.add(e);
    }

    private boolean exists(int entityID, String entityName) {
        return getEntities().stream().anyMatch(e -> e.getEntityID() == entityID) || getEntities().stream().anyMatch(e -> e.getEntityName().equalsIgnoreCase(entityName));
    }

    public void freezeCreatures(Creature except) {
        entities.stream().filter(e -> e instanceof Creature).filter(e -> !e.equals(except)).forEach(e -> ((Creature)e).setFreeze(true));
    }

    public void killAll() {
        entities.forEach(Entity::kill);
    }
}
