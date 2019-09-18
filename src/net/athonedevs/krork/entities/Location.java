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

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.world.World;

import java.util.HashMap;
import java.util.Map;

public class Location {

    private KrorkAPI API;

    private World world;
    private float x;
    private float y;
    private int direction;

    /**
     * Default Location constructor
     * @see World
     *
     * @param world The world where the Entity is at
     * @param x The X Location
     * @param y The y Location
     */
    public Location(World world, float x, float y) {
        this(world, x, y, 0);
    }

    /**
     * Default Location constructor
     * @see World
     * @see Entity
     *
     * @param world The world where the Entity is at
     * @param x The X Location
     * @param y The y Location
     * @param direction The direction where the Entity is looking at
     */
    public Location(World world, float x, float y, int direction) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * Adds values to the Location
     *
     * @param x The X value to add
     * @param y The Y value to add
     */
    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Checks if the Location is the same
     *
     * @param x The X value to check
     * @param y The Y value to check
     * @return True if the location is the same, false if not
     */
    public boolean is(float x, float y) {
        return getX() == x && getY() == y;
    }

    /**
     * Teleports an Entity to a specified location
     * @see Entity
     *
     * @param x The new X value
     * @param y The new Y value
     * @param direction The new Direction value
     */
    public void teleport(float x, float y, int direction) {
        teleport(API.getWorld(), x, y, direction);
    }
    /**
     * Teleports an Entity to a specified location
     * @see Entity
     * @see World
     *
     * @param world the new World value
     * @param x The new X value
     * @param y The new Y value
     * @param direction The new Direction value
     */
    public void teleport(World world, float x, float y, int direction) {
        setWorld(world);
        setX(x);
        setY(y);
        setDirection(direction);
    }

    public int getXDistance(Location location) {
        return (int) (getX() - location.getX());
    }
    public int getYDistance(Location location) {
        return (int) (getY() - location.getY());
    }

    public Map<String, Object> serializeLocation() {
        final Map<String, Object> location = new HashMap<>();

        location.put("world", getWorld().getWorldName());
        location.put("x", getX());
        location.put("y", getY());
        location.put("direction", getDirection());

        return location;
    }

    public String toString() {
        return "Location(API=" + this.API + ", world=" + this.world + ", x=" + this.x + ", y=" + this.y + ", direction=" + this.direction + ")";
    }

    public World getWorld() {
        return this.world;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setAPI(KrorkAPI API) {
        this.API = API;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
