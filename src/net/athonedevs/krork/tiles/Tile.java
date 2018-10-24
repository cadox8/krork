/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 */

package net.athonedevs.krork.tiles;

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.gfx.Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    @Setter public static final int TILEWIDTH = 64, TILEHEIGHT = 64;


    @Setter public static Tile[] tiles = new Tile[256];

    public static Tile bug = new Tile(Sprites.randomImage(TILEWIDTH, TILEHEIGHT), 0);



    @Getter protected BufferedImage texture;
    @Getter protected final int id;
    //protected final double degrees;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {}

    public void render(Graphics g, int x, int y) {
        render(g, x, y,  TILEWIDTH, TILEHEIGHT);
    }
    public void render(Graphics g, int x, int y, int width, int height) {
        g.drawImage(texture, x, y, width, height, null);
    }

    public boolean isSolid() {
        return false;
    }


    @Override
    public String toString() {
        return "Tile{Id: " + id + ", Class: " + getClass() + "}";
    }
}
