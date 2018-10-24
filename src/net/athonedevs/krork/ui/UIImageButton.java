/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 */

package net.athonedevs.krork.ui;

import net.athonedevs.krork.display.Resize;
import net.athonedevs.krork.utils.SizeUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton  extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        SizeUtils resized = Resize.resize((int)x, (int)y, width, height);
        x = resized.getX();
        y = resized.getY();
        width = resized.getWidth();
        height = resized.getHeight();

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

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
