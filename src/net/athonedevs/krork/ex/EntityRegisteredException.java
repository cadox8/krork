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

package net.athonedevs.krork.ex;


import net.athonedevs.krork.entities.Entity;

public class EntityRegisteredException extends Exception {

    public EntityRegisteredException(Entity e) {
        super("The ID or the username of " + e.getEntityName() + " - " + e.getEntityID() + " already exists in database");
    }
}
