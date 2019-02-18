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

import java.awt.*;

public class UIText extends UIObject {

    @Getter @Setter private String text;
    @Getter @Setter private Color color;
    private ClickListener clicker;

    @Getter @Setter private Font font;

    public UIText(float x, float y, Color color, String text) {
        this(x, y, color, text, () -> {});
    }
    public UIText(float x, float y, Color color, String text, ClickListener clicker) {
        super(x, y - 12, 250, 15);
        this.text = text;
        this.color = color;
        this.clicker = clicker;

        font = new Font("Agency FB", Font.PLAIN, 14);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        int lines = 0;
        g.setColor(color);
        g.setFont(font);

        for (String line : text.split("\n")) {
            g.drawString(line, (int)getX(), (int)getY() + (g.getFontMetrics().getHeight() * lines) + 10);
            lines++;
        }
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

    public void addText(String text) {
        setText(getText() + text);
    }
}
