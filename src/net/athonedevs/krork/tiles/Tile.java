package net.athonedevs.krork.tiles;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    @Setter public static Tile[] tiles = new Tile[256];

    @Setter public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

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
