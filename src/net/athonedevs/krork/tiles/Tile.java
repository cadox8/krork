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

import net.athonedevs.krork.animation.Animation;
import net.athonedevs.krork.gfx.Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tile {

    public static int TILEWIDTH = 64, TILEHEIGHT = 64;

    /**
     * Default tile to show if no-one is presented
     */
    public static Tile bug = new Tile(Sprites.randomImage(TILEWIDTH, TILEHEIGHT), 0);

    protected final BufferedImage texture;
    protected final int id;
    protected Animation animation;

    private final List<SubTile> subtiles;

    /**
     * Generates a new Tile
     *
     * In world.txt this should appear as [id]
     *
     * @param texture The texture of the tile
     * @param id The id of the tile
     */
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        subtiles = new ArrayList<>();
    }

    /**
     * Adds a subtile to the Tile
     * @see SubTile
     *
     * @param subTile The subtile to be added
     */
    public void addSubTile(SubTile subTile) {
        subtiles.add(subTile);
    }

    /**
     * Adds an animation to the tile
     * @see Animation
     *
     * @param animation The animation to be added
     */
    public void addAnimation(Animation animation) {
        this.animation = animation;
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

    public SubTile getSubTile(int subID) {
        return getSubtiles().stream().filter(t -> t.getSubID() == subID).findAny().orElse(null);
    }

    @Override
    public String toString() {
        return "Tile{Id: " + id + ", Class: " + getClass() + "}";
    }

    public BufferedImage getTexture() {
        return this.texture;
    }

    public int getId() {
        return this.id;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public List<SubTile> getSubtiles() {
        return this.subtiles;
    }

    public class SubTile {

        private int subID;
        private BufferedImage image;

        /**
         * Creates a subtile for a Tile
         *
         * This is made for a tiles which has two or more states (for example, a rotated tile)
         *
         * The way to add this into the world.txt is [id]:[subID]
         *
         * @param subID The subtile ID
         * @param image The subitile texture
         */
        public SubTile(int subID, BufferedImage image) {
            this.subID = subID;
            this.image = image;
        }

        public int getSubID() {
            return this.subID;
        }

        public BufferedImage getImage() {
            return this.image;
        }

        public void setSubID(int subID) {
            this.subID = subID;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof SubTile)) return false;
            final SubTile other = (SubTile) o;
            if (!other.canEqual((Object) this)) return false;
            if (this.getSubID() != other.getSubID()) return false;
            final Object this$image = this.getImage();
            final Object other$image = other.getImage();
            if (!Objects.equals(this$image, other$image)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof SubTile;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            result = result * PRIME + this.getSubID();
            final Object $image = this.getImage();
            result = result * PRIME + ($image == null ? 43 : $image.hashCode());
            return result;
        }

        public String toString() {
            return "Tile.SubTile(subID=" + this.getSubID() + ", image=" + this.getImage() + ")";
        }
    }
}
