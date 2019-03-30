/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 */

package net.athonedevs.krork.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.world.World;

import java.util.HashMap;
import java.util.Map;

@ToString
public class Location {

    @Setter private KrorkAPI API;

    @Getter @Setter private World world;
    @Getter @Setter private float x;
    @Getter @Setter private float y;
    @Getter @Setter private int direction;

    public Location(World world, float x, float y) {
        this(world, x, y, 0);
    }

    public Location(World world, float x, float y, int direction) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void teleport(float x, float y, int direction) {
        teleport(API.getWorld(), x, y, direction);
    }
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

        location.put("world", getWorld().worldName());
        location.put("x", getX());
        location.put("y", getY());
        location.put("direction", getDirection());

        return location;
    }
}
