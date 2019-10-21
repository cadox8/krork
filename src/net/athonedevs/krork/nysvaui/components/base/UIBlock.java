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

package net.athonedevs.krork.nysvaui.components.base;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.NysvaUI;
import net.athonedevs.krork.nysvaui.helpers.NysvaColor;
import net.athonedevs.krork.nysvaui.helpers.RelativeDimension;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class UIBlock extends NysvaUI {

    private NysvaColor background;

    private boolean rounded = false;

    public UIBlock(KrorkAPI api) {
        this(api, NysvaColor.DARK_GRAY);
    }
    public UIBlock(KrorkAPI api, NysvaColor background) {
        super(api);
        this.background = background;
    }

    @Override
    public void tick() {
        if (!components.isEmpty()) components.forEach(NysvaUI::tick);
    }

    @Override
    public void render(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setColor(background.getColor());
        final Rectangle r = getRelativeDimension().getBounds();

        if (isRounded()) {
            final RoundRectangle2D r2 = new RoundRectangle2D.Double(r.getX(), r.getY(), r.getWidth(), r.getHeight(), 35, 35);
            g2.draw(r2);
            g2.fill(r2);
        } else {
            g2.draw(r);
            g2.fill(r);
        }
        if (!components.isEmpty()) components.forEach(c -> c.render(g));
    }

    @Override
    public void onClick() {
        if (!components.isEmpty()) components.forEach(NysvaUI::onClick);
    }

    public void addUIComponent(NysvaUI component) {
        final RelativeDimension textDimension = component.getRelativeDimension();
        final RelativeDimension rd = new RelativeDimension(textDimension.getX() + getRelativeDimension().getX() + 5, 5 + textDimension.getY() + getRelativeDimension().getY(), textDimension.getWidth(), textDimension.getHeight());
        component.setRelativeDimension(rd);
        component.setMaxWidth(getRelativeDimension().getMaxWidth());
        components.add(component);
        component.setParent(this);
    }

    public NysvaColor getBackground() {
        return background;
    }

    public void setBackground(NysvaColor background) {
        this.background = background;
    }

    public boolean isRounded() {
        return rounded;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }
}
