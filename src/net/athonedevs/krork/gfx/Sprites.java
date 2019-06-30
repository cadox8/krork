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

package net.athonedevs.krork.gfx;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Sprites {

    private final BufferedImage sprites;
    private final int DEFAULT_WIDTH, DEFAULT_HEIGHT;

    /**
     * Default Sprites constructor
     *
     * @param sprites The image to be used
     */
    public Sprites(BufferedImage sprites) {
        this(sprites, 64, 64);
    }

    /**
     * Default Sprites constructor
     *
     * @param sprites The image to be used
     * @param width The width of the sprites
     * @param height The height of the sprites
     */
    public Sprites(BufferedImage sprites, int width, int height) {
        this.sprites = sprites;
        this.DEFAULT_WIDTH = width;
        this.DEFAULT_HEIGHT = height;
    }

    /**
     * Crops the original image in small pieces
     *
     * @param x The x location of the image
     * @param y The y location of the image
     * @return The cropped image
     */
    public BufferedImage crop(int x, int y) {
        return crop(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Crops the original image in small pieces
     *
     * @param x The x location of the image
     * @param y The y location of the image
     * @param width The width of the sprite to be crop
     * @param height The height of the sprite to be crop
     * @return The cropped image
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sprites.getSubimage(x, y, width, height);
    }

    /**
     * Generates a random image
     *
     * @param width The width of the image
     * @param height The height of the image
     * @return A random image
     */
    public static BufferedImage randomImage(int width, int height) {
        return coloredSprite(width, height, new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
    }

    /**
     * Generates a image with a color
     *
     * @param width The width of the image
     * @param height The height of the image
     * @param color The color of the Sprite
     * @return A random image
     */
    public static BufferedImage coloredSprite(int width, int height, Color color) {
        final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int a = color.getAlpha();
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);
            }
        }
        return img;
    }

    /**
     * Rotates a image
     *
     * @param degrees The degrees to rotate
     * @param texture The image to be rotates
     * @return The new rotated image
     */
    public static BufferedImage rotateImage(double degrees, BufferedImage texture) {
        final AffineTransform tx = new AffineTransform();

        tx.translate(texture.getHeight() / 2, texture.getWidth() / 2);
        tx.rotate(Math.toRadians(degrees));
        tx.translate(-texture.getWidth() / 2, -texture.getHeight() / 2);

        return new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR).filter(texture, new BufferedImage(texture.getHeight(), texture.getWidth(), texture.getType()));
    }
}
