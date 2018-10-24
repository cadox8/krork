/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 */

package net.athonedevs.krork.attributes;

import lombok.Getter;
import net.athonedevs.krork.api.KrorkAPI;

public abstract class Attribute {

    protected final KrorkAPI API;
    @Getter private final int id;
    @Getter private final String name;

    public Attribute(KrorkAPI API, int id, String name) {
        this.API = API;
        this.id = id;
        this.name = name;
    }

    public abstract void perform();
}
