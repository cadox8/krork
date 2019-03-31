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

package net.athonedevs.krork.state;

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.api.KrorkAPI;

import java.awt.*;

public abstract class State {

    protected KrorkAPI API;

    @Getter @Setter private static State state = null;

    public State(KrorkAPI API) {
        this.API = API;
    }


    public abstract void tick();
    public abstract void render(Graphics g);
}
