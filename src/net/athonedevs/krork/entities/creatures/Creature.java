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

package net.athonedevs.krork.entities.creatures;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.Entity;
import net.athonedevs.krork.tiles.Tile;

import java.awt.*;


public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;

    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

    protected float speed;
    protected float xMove, yMove;

    protected boolean freeze = false;


    public Creature(KrorkAPI API, int entityID, String entityName, float x, float y, int width, int height) {
        super(API, entityID, entityName, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }


    public void move() {
        if (!checkEntityCollisions(xMove, 0f)) moveX();
        if (!checkEntityCollisions(0f, yMove)) moveY();
    }

    public void moveX() {
        if (xMove > 0) { //Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        } else {
            if (xMove < 0) { //Moving left
                int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

                if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                    x += xMove;
                } else {
                    x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                }
            }
        }
    }

    public void moveY() {
        if (yMove < 0) { //Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        } else {
            if (yMove > 0) { //Down
                int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

                if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                    y += yMove;
                } else {
                    y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                }
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return API.getWorld().getTile(x, y).isSolid();
    }

    @Deprecated
    public void fixAnimations() {
    }

    @Override
    public void preRender(Graphics g) {}

    public float getSpeed() {
        return this.speed;
    }

    public float getXMove() {
        return this.xMove;
    }

    public float getYMove() {
        return this.yMove;
    }

    public boolean isFreeze() {
        return this.freeze;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setXMove(float xMove) {
        this.xMove = xMove;
    }

    public void setYMove(float yMove) {
        this.yMove = yMove;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }
}
