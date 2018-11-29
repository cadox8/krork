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

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.display.Resize;
import net.athonedevs.krork.utils.SizeUtils;

import java.awt.*;

public class UIField extends UIObject {

    @Getter @Setter private String text = "";
    @Getter @Setter private String drawText = "";

    public UIField(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        final SizeUtils resized = Resize.resize((int)x, (int)y, width, height);

        if (!canWrite(g, text, resized)) drawText = text;

        g.setColor(Color.BLACK);
        g.drawRect(resized.getX(), resized.getY(), resized.getWidth(), resized.getHeight());
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(resized.getX(), resized.getY(), resized.getWidth(), resized.getHeight());
        new UIText(x + 2, y + height - 3, Color.BLACK, drawText).render(g);
    }

    @Override
    public void onClick() {

    }

    private boolean canWrite(Graphics g, String text, SizeUtils resized) {
        return g.getFontMetrics(g.getFont()).stringWidth(text) > resized.getWidth();
    }
}