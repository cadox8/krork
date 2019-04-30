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

public interface AStarHeuristic {

    /**
     * Get the additional heuristic cost of the given tile. This controls the
     * order in which tiles are searched while attempting to find a path to the
     * target location. The lower the cost the more likely the tile will
     * be searched.
     *
     * @param map The map on which the path is being found
     * @param entity The entity that is moving along the path
     * @param x The x coordinate of the tile being evaluated
     * @param y The y coordinate of the tile being evaluated
     * @param tx The x coordinate of the target location
     * @param ty Teh y coordinate of the target location
     * @return The cost associated with the given tile
     */
    public float getCost(TileBasedMap map, Entity entity, int x, int y, int tx, int ty);
}
