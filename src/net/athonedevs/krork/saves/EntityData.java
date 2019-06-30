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

public abstract class EntityData {

    public EntityData() {}

    /**
     * Default Entity data class to save the data from the entities to load them later
     *
     */
    private int entityID;
    private String entityData;
    private LocationUtils location;

    public int getEntityID() {
        return this.entityID;
    }

    public String getEntityData() {
        return this.entityData;
    }

    public LocationUtils getLocation() {
        return this.location;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public void setEntityData(String entityData) {
        this.entityData = entityData;
    }

    public void setLocation(LocationUtils location) {
        this.location = location;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EntityData)) return false;
        final EntityData other = (EntityData) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getEntityID() != other.getEntityID()) return false;
        final Object this$entityData = this.getEntityData();
        final Object other$entityData = other.getEntityData();
        if (this$entityData == null ? other$entityData != null : !this$entityData.equals(other$entityData))
            return false;
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EntityData;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getEntityID();
        final Object $entityData = this.getEntityData();
        result = result * PRIME + ($entityData == null ? 43 : $entityData.hashCode());
        final Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        return result;
    }

    public String toString() {
        return "EntityData(entityID=" + this.getEntityID() + ", entityData=" + this.getEntityData() + ", location=" + this.getLocation() + ")";
    }

    public class LocationUtils {

        private String world;
        private float x;
        private float y;
        private int direction;

        public LocationUtils() {
        }

        public String getWorld() {
            return this.world;
        }

        public float getX() {
            return this.x;
        }

        public float getY() {
            return this.y;
        }

        public int getDirection() {
            return this.direction;
        }

        public void setWorld(String world) {
            this.world = world;
        }

        public void setX(float x) {
            this.x = x;
        }

        public void setY(float y) {
            this.y = y;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof LocationUtils)) return false;
            final LocationUtils other = (LocationUtils) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$world = this.getWorld();
            final Object other$world = other.getWorld();
            if (this$world == null ? other$world != null : !this$world.equals(other$world)) return false;
            if (Float.compare(this.getX(), other.getX()) != 0) return false;
            if (Float.compare(this.getY(), other.getY()) != 0) return false;
            if (this.getDirection() != other.getDirection()) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof LocationUtils;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $world = this.getWorld();
            result = result * PRIME + ($world == null ? 43 : $world.hashCode());
            result = result * PRIME + Float.floatToIntBits(this.getX());
            result = result * PRIME + Float.floatToIntBits(this.getY());
            result = result * PRIME + this.getDirection();
            return result;
        }

        public String toString() {
            return "EntityData.LocationUtils(world=" + this.getWorld() + ", x=" + this.getX() + ", y=" + this.getY() + ", direction=" + this.getDirection() + ")";
        }
    }
}
