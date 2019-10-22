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

package net.athonedevs.krork.nysvaui.components.field;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.ClickListener;
import net.athonedevs.krork.nysvaui.NysvaUI;

import java.awt.*;

public class UIField extends NysvaUI {

    private ClickListener clicker;

    private String text;

    public UIField(KrorkAPI api) {
        super(api);

        clicker = () -> {
            api.getKeyManager().setWritingTo(this);
        };
    }

    @Override
    public void tick() {
        if (api.getKeyManager().getWritingTo() == null) return;
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

    private boolean canWrite(Graphics g, String text) {
        return g.getFontMetrics(g.getFont()).stringWidth(text) > getUIDimension().getWidth();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
