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

package net.athonedevs.krork.tiles;

import lombok.*;
import net.athonedevs.krork.gfx.Animation;
import net.athonedevs.krork.gfx.Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    @Setter public static final int TILEWIDTH = 64, TILEHEIGHT = 64;


    @Setter public static Tile[] tiles = new Tile[Integer.MAX_VALUE];

    public static Tile bug = new Tile(Sprites.randomImage(TILEWIDTH, TILEHEIGHT), 0);



    @Getter protected final BufferedImage texture;
    @Getter protected final int id;
    @Getter protected final Animation animation;

    @Getter private final TileData tileData;

    public Tile(BufferedImage texture, int id) {
        this(texture, id, null);
    }
    public Tile(BufferedImage texture, int id, Animation animation) {
        this.texture = texture;
        this.id = id;
        this.animation = animation;

        tileData = new TileData(texture.getRGB(0, 0), (byte) 0);

        tiles[id] = this;
    }

    public void tick() {
        if (animation != null) animation.tick();
    }

    public void render(Graphics g, int x, int y) {
        render(g, x, y,  TILEWIDTH, TILEHEIGHT);
    }
    public void render(Graphics g, int x, int y, int width, int height) {
        if (animation != null) {
            g.drawImage(animation.getCurrentFrame(), x, y, width, height, null);
        } else {
            g.drawImage(texture, x, y, width, height, null);
        }
    }

    public boolean isSolid() {
        return false;
    }


    @Override
    public String toString() {
        return "Tile{Id: " + id + ", Class: " + getClass() + "}";
    }

    @AllArgsConstructor
    @ToString
    @Data
    public class TileData {

        private final int tileColor;
        private final byte data;
    }
}
