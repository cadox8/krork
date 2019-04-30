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

package net.athonedevs.krork.ai.path;

import net.athonedevs.krork.entities.Entity;

public class ClosestHeuristic implements AStarHeuristic {
    /**
     * @see AStarHeuristic#getCost(TileBasedMap, Entity, int, int, int, int)
     */
    public float getCost(TileBasedMap map, Entity entity, int x, int y, int tx, int ty) {
        float dx = tx - x;
        float dy = ty - y;

        return (float) (Math.sqrt((dx*dx)+(dy*dy)));
    }
}
