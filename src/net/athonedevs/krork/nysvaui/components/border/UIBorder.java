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

package net.athonedevs.krork.nysvaui.components.border;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.NysvaUI;
import net.athonedevs.krork.nysvaui.helpers.NysvaColor;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class UIBorder extends NysvaUI {

    private NysvaColor background;

    private boolean rounded = false;
    private int borderSize;

    private int borderRadius = 30;

    public UIBorder(KrorkAPI krorkAPI) {
        this(krorkAPI, 2);
    }
    public UIBorder(KrorkAPI krorkAPI, int borderSize) {
        this(krorkAPI, NysvaColor.DARK_GRAY, borderSize);
    }
    public UIBorder(KrorkAPI krorkAPI, NysvaColor background, int borderSize) {
        super(krorkAPI);
        this.background = background;
        this.borderSize = borderSize;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setColor(background.getColor());
        g2.setStroke(new BasicStroke(borderSize));
        final Rectangle r = getUIDimension().getBounds();

        if (isRounded()) {
            g2.draw(new RoundRectangle2D.Double(r.getX(), r.getY(), r.getWidth(), r.getHeight(), borderRadius, borderRadius));
        } else {
            g2.draw(r);
        }
    }

    @Override
    public void onClick() {}

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

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
}
