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

package net.athonedevs.krork.dialog;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.creatures.Creature;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Dialog {

    private final KrorkAPI krorkAPI;
    private final Creature entity;
    private final BufferedImage texture;

    private final List<String> text;
    private int page = 0;

    private boolean end = false;

    public Dialog(KrorkAPI krorkAPI, Creature entity, BufferedImage texture) {
        this.krorkAPI = krorkAPI;
        this.entity = entity;
        this.texture = texture;
        text = new ArrayList<>();
    }

    public Dialog addText(List<String> newText) {
        final List<String> tempList = new ArrayList<>();
        text.addAll(tempList);
        return this;
    }

    public Dialog replace(String key, String newText) {
        text.forEach(t -> text.set(text.indexOf(t), t.replaceAll(key, newText)));
        return this;
    }


    public void tick() {
        if (!end) {
            entity.setFreeze(true);
        } else {
            entity.setFreeze(false);
        }

        if (krorkAPI.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) page++;
        if (krorkAPI.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
            if (page == 0) return;
            page--;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(texture, 70, krorkAPI.getHeight() - 125, krorkAPI.getWidth() - 120, 100, null);

        int p = 1;
        for (String s : renderText()) {
            g.setColor(Color.WHITE);
            g.drawString(s, 150, krorkAPI.getHeight() - 122 + (p * 20));
            p++;
        }
    }

    private List<String> renderText() {
        final List<String> temp = new ArrayList<>();
        int index = page * 4;

        try {
            temp.addAll(text.subList(index, text.size() >= (index + 4) ? index + 4 : text.size()));
        } catch (IllegalArgumentException e) { // Problems? Nope
            end = true;
        }
        if (temp.isEmpty()) end = true;
        if (end) return new ArrayList<>();
        return temp;
    }

    public boolean isEnd() {
        return this.end;
    }
}
