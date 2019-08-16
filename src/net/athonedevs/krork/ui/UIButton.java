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

public class UIButton extends UIText {

    private ClickListener clicker;

    /**
     * Generates a Text Button Object
     *
     * @param x The x position to be shown
     * @param y The y position to be shown
     * @param width The width of the object
     * @param height The height of the object
     * @param color The color of the text
     * @param bgColor The color of the button
     * @param text The text to be shown
     * @param clicker Executes the inside code on click
     */
    public UIButton(float x, float y, int width, int height, Color color, Color bgColor, String text, ClickListener clicker) {
        super(x, y, color, text);
        this.clicker = clicker;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(getColor());
        g.setFont(getFont());

        g.drawRect((int)getX(), (int)getY(), width, height);
        g.drawString(getText(), (int)getX() + 5 , (int)getY() + (height / 2));
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
