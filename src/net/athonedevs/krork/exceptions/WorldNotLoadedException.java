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

package net.athonedevs.krork.exceptions;

import net.athonedevs.krork.entities.Entity;
import net.athonedevs.krork.world.World;

public class WorldNotLoadedException extends RuntimeException {

    public WorldNotLoadedException(Entity entity, World world) {
        super("You are trying to add an Entity (" + entity.getEntityID() + " - " + entity.getEntityName() + ") into a unloaded World (Current: " + world.getWorldName() + ")");
    }
}