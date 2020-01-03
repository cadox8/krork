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

import net.athonedevs.krork.animation.Animation;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.NysvaUI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageAnimation extends NysvaUI {

    private Animation anim;

    /**
     * Generates a Image Animation Object
     *
     * @see Animation
     *
     * @param krorkAPI
     * @param anim The animation to be shown
     */
    public UIImageAnimation(KrorkAPI krorkAPI, Animation anim) {
        super(krorkAPI);
        this.anim = anim;
    }

    @Override
    public void tick() {
        anim.tick();
    }

    @Override
    public void render(Graphics g) {
        drawImage(g, new BufferedImage[]{anim.getCurrentFrame()});
    }

    @Override
    public void onClick() {

    }
}
