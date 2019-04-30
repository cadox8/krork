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

public interface PathFinder {

    /**
     * Find a path from the starting location provided (sx,sy) to the target
     * location (tx,ty) avoiding blockages and attempting to honour costs
     * provided by the tile map.
     *
     * @param entity The entity that will be moving along the path.
     *
     * @param sx The x coordinate of the start location
     * @param sy The y coordinate of the start location
     * @param tx The x coordinate of the target location
     * @param ty Teh y coordinate of the target location
     * @return The path found from start to end, or null if no path can be found.
     */
    Path findPath(Entity entity, int sx, int sy, int tx, int ty);
}
