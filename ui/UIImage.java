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

package net.athonedevs.krork.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject {

    private BufferedImage[] images;

    /**
     * Generates a Image Object
     *
     * @param x The x position to be shown
     * @param y The y position to be shown
     * @param width The width of the object
     * @param height The height of the object
     * @param image The image to be shown
     */
    public UIImage(float x, float y, int width, int height, BufferedImage image) {
        this(x, y, width, height, new BufferedImage[]{image});
    }

    /**
     * Generates a Image Object
     *
     * @param x The x position to be shown
     * @param y The y position to be shown
     * @param width The width of the object
     * @param height The height of the object
     * @param images The images to be shown
     */
    public UIImage(float x, float y, int width, int height, BufferedImage[] images) {
        super(x, y, width, height);
        this.images = images;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        x = getX();
        y = getY();
        width = getWidth();
        height = getHeight();

        synchronized (images) {
            if (hovering) {
                if (images.length > 1) {
                    g.drawImage(images[1], (int) x, (int) y, width, height, null);
                } else {
                    g.drawImage(images[0], (int) x, (int) y, width + 5, height + 5, null);
                }
            } else {
                g.drawImage(images[0], (int) x, (int) y, width, height, null);
            }
        }
    }

    @Override
    public void onClick() {}

    /**
     * Changes the images on the fly
     *
     * @param imgs The new images
     */
    public synchronized void changeImages(BufferedImage... imgs) {
        images = imgs;
    }

    /**
     * Changes an image on the fly
     *
     * @param id The image id
     * @param img The new image
     */
    public synchronized void changeImage(int id, BufferedImage img) {
        images[id] = img;
    }

    /**
     * Gets all the images
     *
     * @return The images
     */
    public BufferedImage[] getImages() {
        return this.images;
    }
}
