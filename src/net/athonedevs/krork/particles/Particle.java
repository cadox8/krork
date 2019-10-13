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

package net.athonedevs.krork.particles;

import net.athonedevs.krork.animation.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Particle {

    private BufferedImage[] images;
    private int velocity;

    private final Animation animation;

    public Particle(BufferedImage[] images, int velocity) {
        animation = new Animation(velocity * 100, images);
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(animation.getCurrentFrame(), x, y, null);
    }

    public void tick() {
        animation.tick();
    }

    public Particle withEnd() {
        animation.withEnd(true);
        return this;
    }
}
