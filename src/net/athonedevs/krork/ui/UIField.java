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
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.utils.SizeUtils;

import java.awt.*;

public class UIField extends UIObject {

    private ClickListener clicker;

    @Getter @Setter private String text = "";

    @Getter @Setter private Color baseColor = new Color(217, 217, 217);

    public UIField(float x, float y, int width, int height, KrorkAPI api) {
        super(x, y - height, width, height);

        this.clicker = () -> api.getKeyManager().setWritingTo(this);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        String drawText = "";

        if (!canWrite(g, text)) drawText = text;

        g.setColor(Color.BLACK);
        g.drawRect((int)getX(), (int)getY(), getWidth(), getHeight());

        g.setColor(baseColor);
        g.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());
        new UIText(x + 2, y + height - (height / 5f) - 10, Color.BLACK, drawText).render(g);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

    private boolean canWrite(Graphics g, String text) {
        return g.getFontMetrics(g.getFont()).stringWidth(text) > width;
    }
}
