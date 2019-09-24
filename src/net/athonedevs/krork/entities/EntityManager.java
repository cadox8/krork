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

import net.athonedevs.krork.annotations.Experimental;
import net.athonedevs.krork.annotations.NotNull;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.creatures.Creature;
import net.athonedevs.krork.exceptions.WorldNotLoadedException;
import net.athonedevs.krork.utils.Log;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager {

    private KrorkAPI API;

    private ArrayList<Entity> entities;

    private Comparator<Entity> renderSorter = (Entity a, Entity b)-> {
        if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) return -1;
        return 0;
    };

    /**
     * Default EntityManager constructor
     *
     * @param API The KrorkAPI
     */
    public EntityManager(KrorkAPI API) {
        this.API = API;
        entities = new ArrayList<>();
    }

    public synchronized void tick() {
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
        entities.forEach(e -> e.preRender(g));
        entities.forEach(e -> e.render(g));
    }

    /**
     * Adds an entity to the World
     *
     * @param entity The entity to be added
     */
    public synchronized void addEntity(@NotNull Entity entity) {
        try {
            if (entity.getLocation() == null || !entity.getLocation().getWorld().getWorldName().equalsIgnoreCase(API.getWorld().getWorldName())) throw new WorldNotLoadedException(entity, API.getWorld());
            entities.add(entity);
        } catch (WorldNotLoadedException e) {
            Log.log(Log.LogType.DANGER, e.getMessage());
            System.exit(11);
        }
    }

    /**
     * Gets the Entity which are at the given location
     * @see Location
     * @see Entity
     *
     * @param x The X value of the Location
     * @param y The Y value of the Location
     * @return The List of entities at the Location
     */
    @Experimental
    public List<Entity> getEntityOnLoc(float x, float y) {
        return API.getWorld().getEntityManager().getEntities().stream().filter(e -> e.getBounds().contains((int)x, (int)y)).collect(Collectors.toList());
    }

    /**
     * Freezes all Creatures except the one selected
     *
     * @param except The creature to no be freeze
     */
    public void freezeCreatures(Creature except) {
        entities.stream().filter(e -> e instanceof Creature).filter(e -> !e.equals(except)).forEach(e -> ((Creature)e).setFreeze(true));
    }

    /**
     * Freezes all Creatures
     */
    public void freezeCreatures() {
        entities.stream().filter(e -> e instanceof Creature).forEach(e -> ((Creature)e).setFreeze(true));
    }

    /**
     * Kills all entities
     */
    public void killAll() {
        entities.forEach(Entity::kill);
    }

    public KrorkAPI getAPI() {
        return this.API;
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public void setAPI(KrorkAPI API) {
        this.API = API;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
