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

package net.athonedevs.krork.attributes;

import net.athonedevs.krork.api.KrorkAPI;

public abstract class Attribute {

    protected final KrorkAPI krorkAPI;
    private final int id;
    private final String name;

    /**
     * Creates a new Attribute
     *
     * @param krorkAPI The KrorkAPI
     * @param id The Attribute ID
     * @param name The Attribute Name
     */
    public Attribute(KrorkAPI krorkAPI, int id, String name) {
        this.krorkAPI = krorkAPI;
        this.id = id;
        this.name = name;
    }

    public abstract void perform();

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
