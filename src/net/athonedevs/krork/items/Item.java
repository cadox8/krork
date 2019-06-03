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

package net.athonedevs.krork.items;

import net.athonedevs.krork.attributes.Attribute;
import net.athonedevs.krork.ex.ItemRegisteredException;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Item {

    private final int id;
    private String name;
    private BufferedImage texture;

    private List<Attribute> attributes;

    public Item(int id, String name, BufferedImage texture) throws ItemRegisteredException {
        this.id = id;
        this.name = name;
        this.texture = texture;

        attributes = new ArrayList<>();
    }

    public abstract void use();

    public void performAttributes() {
        attributes.forEach(Attribute::perform);
    }

    public String toString() {
        return "Item(id=" + this.id + ", name=" + this.name + ", texture=" + this.texture + ", attributes=" + this.attributes + ")";
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public BufferedImage getTexture() {
        return this.texture;
    }

    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
