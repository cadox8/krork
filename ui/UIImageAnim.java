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

import net.athonedevs.krork.animation.Animation;

import java.awt.*;

public class UIImageAnim extends UIObject {

    private Animation anim;
    private ClickListener clicker;

    /**
     * Generates a Image Animation Object
     *
     * @see Animation
     *
     * @param x The x position to be shown
     * @param y The y position to be shown
     * @param width The width of the object
     * @param height The height of the object
     * @param anim The animation to be shown
     * @param clicker Executes the inside code on click
     */
    public UIImageAnim(float x, float y, int width, int height, Animation anim, ClickListener clicker) {
        super(x, y, width, height);
        this.anim = anim;
        this.clicker = clicker;
    }

    @Override
    public void tick() {
        anim.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(anim.getCurrentFrame(), (int)getX(), (int)getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
