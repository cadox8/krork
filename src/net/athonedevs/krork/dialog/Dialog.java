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

    /**
     * Default Dialog constructor
     *
     * @param krorkAPI The KrorkAPI
     * @param entity The entity who has the Dialog
     * @param texture The texture of the background
     */
    public Dialog(KrorkAPI krorkAPI, Creature entity, BufferedImage texture) {
        this.krorkAPI = krorkAPI;
        this.entity = entity;
        this.texture = texture;
        text = new ArrayList<>();
    }

    /**
     * Add text lines to the Dialog
     *
     * @param newText The new lines to be added
     * @return This class
     */
    public Dialog addText(List<String> newText) {
        text.addAll(newText);
        return this;
    }

    /**
     * Replaces the selected key to new one
     *
     * @param key The word(s) to be replaced
     * @param newText The new worlds
     * @return This class
     */
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
        int index = page * 4;
        if (index > text.size()) return new ArrayList<>();
        final List<String> temp = new ArrayList<>(text.subList(index, Math.min(text.size(), (index + 4))));
        if (temp.isEmpty()) return new ArrayList<>();
        return temp;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
    public boolean isEnd() {
        return this.end;
    }
}
