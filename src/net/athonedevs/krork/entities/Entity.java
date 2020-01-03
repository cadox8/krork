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

import net.athonedevs.krork.animation.Animation;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.utils.Log;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Entity {

    private final int entityID;
    private final String entityName;

    protected final double DEFAULT_HEALTH = 10;
    protected final double DEFAULT_DAMAGE = 3;
    protected final double DEFAULT_ARMOR = 0;

    protected KrorkAPI krorkAPI;

    protected float x, y;
    protected int width, height;
    private double health;
    private double damage;
    private double armor;
    private double maxHealth;

    private boolean damageable = true;

    // Attack timer
    protected long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;

    protected int direction = 0; //0 = South, 1 = North, 2 = East, 3 = West

    private boolean active = true;

    private Location location;

    protected Rectangle bounds;

    private Entity collisionEntity;

    protected Entity killer;

    protected Animation[] animations = new Animation[4];
    protected BufferedImage texture;

    protected boolean collidable = true;

    private List<Integer> collisionID;

    public Entity(KrorkAPI krorkAPI, int entityID, String entityName, float x, float y, int width, int height) {
        this.krorkAPI = krorkAPI;
        this.entityID = entityID;
        this.entityName = entityName;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        health = DEFAULT_HEALTH;
        damage = DEFAULT_DAMAGE;
        armor = DEFAULT_ARMOR;

        collisionID = new ArrayList<>();

        this.location = new Location(krorkAPI.getWorld(), x, y, direction);
        location.setAPI(krorkAPI);

        bounds = new Rectangle(0, 0, width, height);
    }


    public abstract void tick();
    public abstract void fixAnimations();
    public abstract void render(Graphics g);
    public abstract void preRender(Graphics g);
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
        for (Entity e : krorkAPI.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                if (!getCollisionID().contains(e.getEntityID())) return false;
                collisionEntity = e;
                return true;
            }
        }
        return false;
    }

    public Entity getEntityCollision(float xOffset, float yOffset) {
        for (Entity e : krorkAPI.getWorld().getEntityManager().getEntities()) {
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

    public void addCollisionIDs(Integer... ids) {
        collisionID.addAll(Arrays.asList(ids));
    }

    public Location getLocation() {
        return new Location(krorkAPI.getWorld(), getX(), getY(), getDirection());
    }

    public String toString() {
        return "Entity(entityID=" + this.entityID + ", entityName=" + this.entityName + ", DEFAULT_HEALTH=" + this.DEFAULT_HEALTH + ", DEFAULT_DAMAGE=" + this.DEFAULT_DAMAGE + ", DEFAULT_ARMOR=" + this.DEFAULT_ARMOR + ", krorkAPI=" + this.krorkAPI + ", x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", health=" + this.health + ", damage=" + this.damage + ", armor=" + this.armor + ", maxHealth=" + this.maxHealth + ", damageable=" + this.damageable + ", lastAttackTimer=" + this.lastAttackTimer + ", attackCooldown=" + this.attackCooldown + ", attackTimer=" + this.attackTimer + ", direction=" + this.direction + ", active=" + this.active + ", location=" + this.getLocation() + ", bounds=" + this.bounds + ", collisionEntity=" + this.collisionEntity + ", killer=" + this.killer + ", animations=" + Arrays.deepToString(this.animations) + ", texture=" + this.texture + ", collidable=" + this.collidable + ", collisionID=" + this.collisionID + ")";
    }

    public int getEntityID() {
        return this.entityID;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public KrorkAPI getAPI() {
        return this.krorkAPI;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public double getHealth() {
        return this.health;
    }

    public double getDamage() {
        return this.damage;
    }

    public double getArmor() {
        return this.armor;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public boolean isDamageable() {
        return this.damageable;
    }

    public long getLastAttackTimer() {
        return this.lastAttackTimer;
    }

    public long getAttackCooldown() {
        return this.attackCooldown;
    }

    public long getAttackTimer() {
        return this.attackTimer;
    }

    public int getDirection() {
        return this.direction;
    }

    public boolean isActive() {
        return this.active;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public Entity getCollisionEntity() {
        return this.collisionEntity;
    }

    public Entity getKiller() {
        return this.killer;
    }

    public Animation[] getAnimations() {
        return this.animations;
    }

    public BufferedImage getTexture() {
        return this.texture;
    }

    public boolean isCollidable() {
        return this.collidable;
    }

    public List<Integer> getCollisionID() {
        return this.collisionID;
    }

    public void setAPI(KrorkAPI krorkAPI) {
        this.krorkAPI = krorkAPI;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setDamageable(boolean damageable) {
        this.damageable = damageable;
    }

    public void setLastAttackTimer(long lastAttackTimer) {
        this.lastAttackTimer = lastAttackTimer;
    }

    public void setAttackCooldown(long attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public void setAttackTimer(long attackTimer) {
        this.attackTimer = attackTimer;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void setCollisionEntity(Entity collisionEntity) {
        this.collisionEntity = collisionEntity;
    }

    public void setKiller(Entity killer) {
        this.killer = killer;
    }

    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }
}
