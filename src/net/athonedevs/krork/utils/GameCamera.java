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

    private KrorkAPI krorkAPI;
    private float xOffset, yOffset;

    /**
     * The default Game Camera constructor.
     * This class will set the camera on a location to render the world
     *
     * @param krorkAPI The KrorkAPI
     * @param xOffset The xOffset to start
     * @param yOffset The yOffset to start
     */
    public GameCamera(KrorkAPI krorkAPI, float xOffset, float yOffset) {
        this.krorkAPI = krorkAPI;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    private void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > krorkAPI.getWorld().getWidth() * Tile.TILEWIDTH - krorkAPI.getWidth()) {
            xOffset = krorkAPI.getWorld().getWidth() * Tile.TILEWIDTH - krorkAPI.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > krorkAPI.getWorld().getHeight() * Tile.TILEHEIGHT - krorkAPI.getHeight()) {
            yOffset = krorkAPI.getWorld().getHeight() * Tile.TILEHEIGHT - krorkAPI.getHeight();
        }
    }

    /**
     * Centers the camera on an entity
     *
     * @param entity The entity to be centered on
     */
    public void centerOnEntity(Entity entity) {
        xOffset = entity.getX() - krorkAPI.getWidth() / 2 + entity.getWidth() / 2;
        yOffset = entity.getY() - krorkAPI.getHeight() / 2 + entity.getHeight() / 2;
        checkBlankSpace();
    }

    /**
     * Moves to camera to a direction
     *
     * @param xAmt The x coords to move
     * @param yAmt The y coords to move
     */
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
