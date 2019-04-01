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

package net.athonedevs.krork.tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileManager {

    private static List<Tile> tiles;

    public TileManager() {
        tiles = new ArrayList<>();
    }

    public void addTiles(Tile... tile) {
        tiles.addAll(Arrays.asList(tile));
    }

    public void removeTile(int tileID) {
        tiles.stream().filter(t -> t.getId() == tileID).findAny().ifPresent(t -> tiles.remove(t));
    }

    public static Tile getTile(int tileID) {
        return tiles.stream().filter(t -> t.getId() == tileID).findAny().orElse(Tile.bug);
    }
}
