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

package net.athonedevs.krork.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.gfx.Animation;
import net.athonedevs.krork.utils.Log;

import java.awt.*;
import java.awt.image.BufferedImage;

@ToString
public abstract class Entity {

    @Getter private final int entityID;
    @Getter private final String entityName;

    protected final double DEFAULT_HEALTH = 10;
    protected final double DEFAULT_DAMAGE = 3;
    protected final double DEFAULT_ARMOR = 0;

    @Getter @Setter protected KrorkAPI API;

    @Getter @Setter protected float x, y;
    @Getter @Setter protected int width, height;
    @Getter @Setter private double health;
    @Getter @Setter private double damage;
    @Getter @Setter private double armor;
    @Getter @Setter private double maxHealth;

    @Getter @Setter private boolean damageable = true;

    // Attack timer
    @Getter @Setter protected long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;

    @Getter @Setter protected int direction = 0; //0 = South, 1 = North, 2 = East, 3 = West

    @Getter @Setter private boolean active = true;

    @Setter private Location location;

    @Getter @Setter protected Rectangle bounds;

    @Getter @Setter private Entity collisionEntity;

    @Getter @Setter protected Entity killer;

    @Getter @Setter protected Animation[] animations = new Animation[4];
    @Getter @Setter protected BufferedImage texture;

    @Getter @Setter protected boolean collidable = true;

    public Entity(KrorkAPI API, int entityID, String entityName, float x, float y, int width, int height) {
        this.API = API;
        this.entityID = entityID;
        this.entityName = entityName;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        health = DEFAULT_HEALTH;
        damage = DEFAULT_DAMAGE;
        armor = DEFAULT_ARMOR;

        this.location = new Location(API.getWorld(), x, y, direction);
        location.setAPI(API);

        bounds = new Rectangle(0, 0, width, height);
    }


    public abstract void tick();
    public abstract void fixAnimations();
    public abstract void render(Graphics g);
    public abstract void specialRender(Graphics g);
    public abstract void die();


    public void hurt(Entity attacker) {
        if (!isDamageable()) return;

        double amt = attacker.getDamage();
        health -= amt;

        if (KrorkAPI.isDebugEnabled()) Log.log("Health: " + getHealth());

        if (health <= 0) {
            active = false;
            this.killer = attacker;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        if (!isCollidable()) return false;
        for (Entity e : API.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                collisionEntity = e;
                return true;
            }
        }
        return false;
    }

    public Entity getEntityCollision(float xOffset, float yOffset) {
        for (Entity e : API.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) return e;
        }
        return null;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public void kill() {
        setHealth(0);
    }

    public Location getLocation() {
        return new Location(API.getWorld(), getX(), getY(), getDirection());
    }
}
