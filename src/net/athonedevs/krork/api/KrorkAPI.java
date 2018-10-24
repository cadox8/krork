/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 */

package net.athonedevs.krork.api;


import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.Krork;
import net.athonedevs.krork.utils.GameCamera;
import net.athonedevs.krork.world.World;

public class KrorkAPI {

    @Getter @Setter private static boolean debugEnabled = false;


    @Getter @Setter private Krork game;
    @Getter @Setter private World world;


    public KrorkAPI(Krork game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }
}
