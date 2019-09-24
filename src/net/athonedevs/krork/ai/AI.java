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

package net.athonedevs.krork.ai;

import net.athonedevs.krork.annotations.Experimental;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.Entity;

import java.awt.*;

@Experimental
public abstract class AI {

    protected KrorkAPI API;
    private Entity entity;

    private float speed;
    private int delay;
    protected int direction = 0;
    protected boolean noAI = false;
    protected Rectangle bounds;
    private int tempDelay = 0;

    public AI(KrorkAPI API, Entity entity, float speed, int delay) {
        this(API, entity, speed, delay, new Rectangle(0, 0, 0, 0));
    }
    public AI(KrorkAPI API, Entity entity, float speed, int delay, Rectangle bounds) {
        this.API = API;
        this.entity = entity;
        this.speed = speed;
        this.delay = delay;

        tempDelay = delay;

        this.bounds = bounds;
    }

    public abstract void getMove();
    protected abstract boolean isTracking();
    public abstract void attack(float xMove, float yMove);


    protected void move(int x, int y) {
    }

    private boolean isOnDelay() {
        return tempDelay != 0;
    }

    //TODO: Change
    protected Rectangle getTrackingArea() {
        return new Rectangle((int) (entity.getX() - (entity.getX() / 4)) + 8, (int) (entity.getY() - (entity.getY() / 4)), bounds.width, bounds.height);
    }

    public boolean isNoAI() {
        return this.noAI;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }
}
