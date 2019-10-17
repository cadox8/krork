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

import net.athonedevs.krork.utils.Utils;

import java.awt.*;

public class UIBorder extends UIObject {

    private Color color;

    /**
     * Default UIBorder constructor
     *
     * @param x      The x position to be shown
     * @param y      The y position to be shown
     * @param width  The width of the object
     * @param height The height of the object
     * @param color  The color of the border
     */
    public UIBorder(float x, float y, int width, int height, String color) {
        this(x, y, width, height, Utils.parseHex(color));
    }

    /**
     * Default UIBorder constructor
     *
     * @param x      The x position to be shown
     * @param y      The y position to be shown
     * @param width  The width of the object
     * @param height The height of the object
     * @param color  The color of the border
     */
    public UIBorder(float x, float y, int width, int height, Color color) {
        super(x, y, width, height);

        this.color = color;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.drawRect((int)x, (int)y, width, height);
    }

    @Override
    public void onClick() {
    }

    /**
     * Changes the color of the border
     *
     * @param hex The new color value
     */
    public void changeColor(String hex) {
        changeColor(Utils.parseHex(hex));
    }
    /**
     * Changes the color of the border
     *
     * @param color The new color value
     */
    public void changeColor(Color color) {
        this.color = color;
    }
}
