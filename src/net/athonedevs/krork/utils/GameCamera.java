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

package net.athonedevs.krork.utils;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.Entity;
import net.athonedevs.krork.tiles.Tile;

public class GameCamera {

    private KrorkAPI API;
    private float xOffset, yOffset;

    public GameCamera(KrorkAPI API, float xOffset, float yOffset) {
        this.API = API;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    private void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > API.getWorld().getWidth() * Tile.TILEWIDTH - API.getWidth()) {
            xOffset = API.getWorld().getWidth() * Tile.TILEWIDTH - API.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > API.getWorld().getHeight() * Tile.TILEHEIGHT - API.getHeight()) {
            yOffset = API.getWorld().getHeight() * Tile.TILEHEIGHT - API.getHeight();
        }
    }

    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - API.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - API.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public float getXOffset() {
        return this.xOffset;
    }

    public float getYOffset() {
        return this.yOffset;
    }
}
