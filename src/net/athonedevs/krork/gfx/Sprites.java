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

import lombok.AllArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;

@AllArgsConstructor
public class Sprites {

    private BufferedImage sprites;

    public BufferedImage crop(int x, int y, int width, int height) {
        return sprites.getSubimage(x, y, width, height);
    }

    public static BufferedImage randomImage(int width, int height) {
        return coloredSprite(width, height, new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
    }

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
}
