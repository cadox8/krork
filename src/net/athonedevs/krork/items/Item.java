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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.athonedevs.krork.attributes.Attribute;
import net.athonedevs.krork.ex.ItemRegisteredException;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@ToString
public abstract class Item {

    public static Item[] items = new Item[Integer.MAX_VALUE];

    @Getter private final int id;
    @Getter @Setter private String name;
    @Getter @Setter private BufferedImage texture;

    @Getter @Setter private List<Attribute> attributes;

    public Item(int id, String name, BufferedImage texture) throws ItemRegisteredException {
        this.id = id;
        this.name = name;
        this.texture = texture;

        attributes = new ArrayList<>();

        if (items[id] != null) throw new ItemRegisteredException(id);
        items[id] = this;
    }

    public abstract void use();

    public void performAttributes() {
        attributes.forEach(Attribute::perform);
    }
}
