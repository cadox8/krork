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

package net.athonedevs.krork.saves;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class EntityData {

    public EntityData() {}

    /**
     * Default Entity data class to save the data from the entities to load them later
     *
     */
    private int entityID;
    private String entityData;
    private LocationUtils location;

    @Data
    public class LocationUtils {

        private String world;
        private float x;
        private float y;
        private int direction;
    }
}
