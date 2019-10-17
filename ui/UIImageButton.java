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

import java.awt.image.BufferedImage;

public class UIImageButton extends UIImage {

    private ClickListener clicker;

    /**
     * Generates a Image Button Object
     *
     * @param x The x position to be shown
     * @param y The y position to be shown
     * @param width The width of the object
     * @param height The height of the object
     * @param images The images (changes on hover) to be shown
     * @param clicker Executes the inside code on click
     */
    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height, images);
        this.clicker = clicker;
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}