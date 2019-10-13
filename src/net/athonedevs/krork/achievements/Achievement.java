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

package net.athonedevs.krork.achievements;

import net.athonedevs.krork.animation.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Achievement {

    private final int id;
    private final String name;
    private final Animation icon;
    private String description;

    private int x, y;
    private boolean show = false;

    /**
     * Creates a new Achievement
     *
     * @param id The ID assigned
     * @param name The name to be displayed
     * @param icon The icon to be displayed
     * @param description The name to be displayed
     */
    public Achievement(int id, String name, BufferedImage icon, String description) {
        this(id, name, new Animation(0, new BufferedImage[]{icon}), description);
    }

    /**
     * Creates a new Achievement
     * @see Animation
     *
     * @param id The ID assigned
     * @param name The name to be displayed
     * @param icon The icon to be displayed
     * @param description The name to be displayed
     */
    public Achievement(int id, String name, Animation icon, String description) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
    }

    /**
     * Shows the Achievement in the selected location
     *
     * @param x The X position of the image
     * @param y The Y position of the image
     * @return This class
     */
    public Achievement show(int x, int y) {
        this.x = x;
        this.y = y;
        this.show = true;
        return this;
    }

    public void tick() {
        icon.tick();
    }

    public void render(Graphics g) {
        g.drawImage(icon.getCurrentFrame(), getX(), getY(), icon.getCurrentFrame().getWidth(), icon.getCurrentFrame().getHeight(), null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Animation getIcon() {
        return icon;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Achievements{" + "id=" + id + ", name='" + name + ", icon=" + icon + ", description='" + description + ", x=" + x + ", y=" + y + '}';
    }
}
