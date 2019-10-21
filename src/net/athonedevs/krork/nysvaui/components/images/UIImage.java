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

package net.athonedevs.krork.nysvaui.components.images;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.NysvaUI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends NysvaUI {

    private BufferedImage image;

    /**
     * Generates a Image Object
     *
     * @param api
     * @param image The image to be shown
     */
    public UIImage(KrorkAPI api, BufferedImage image) {
        super(api);
        this.image = image;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        drawImage(g, image);
    }

    @Override
    public void onClick() {}

    /**
     * Changes an image on the fly
     *
     * @param newImage The new image
     */
    public synchronized void changeImage(BufferedImage newImage) {
        image = newImage;
    }

    /**
     * Gets all the images
     *
     * @return The images
     */
    public BufferedImage getImage() {
        return this.image;
    }
}
