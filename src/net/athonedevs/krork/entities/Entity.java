package net.athonedevs.krork.entities;

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.gfx.Animation;
import net.athonedevs.krork.utils.Location;
import net.athonedevs.krork.utils.Log;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {


    protected final int DEFAULT_HEALTH = 10;
    protected final int DEFAULT_DAMAGE = 3;
    protected final float DEFAULT_ARMOR = 0;

    @Getter @Setter protected KrorkAPI API;

    @Getter @Setter protected float x, y;
    @Getter @Setter protected int width, height;
    @Getter @Setter private int health;
    @Getter @Setter private int damage;
    @Getter @Setter private double armor;
    @Getter @Setter private int maxHealth;

    @Getter @Setter private boolean damageable = true;

    // Attack timer
    @Getter @Setter protected long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;

    @Getter @Setter protected int direction = 0; //0 = South, 1 = North, 2 = East, 3 = West

    @Getter @Setter private boolean active = true;

    @Setter private Location location;

    @Getter @Setter protected Rectangle bounds;

    @Getter @Setter private Entity collisionEntity;

    @Getter @Setter protected Entity killer;

    @Getter @Setter protected Animation animDown, animUp, animLeft, animRight;
    @Getter @Setter protected Animation[] animations = new Animation[4];

    public Entity(KrorkAPI API, float x, float y, int width, int height) {
        this.API = API;
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

        int amt = attacker.getDamage();
        health -= amt;

        if (KrorkAPI.isDebugEnabled()) Log.log("Health: " + getHealth());

        if (health <= 0) {
            active = false;
            this.killer = attacker;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
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


    protected BufferedImage getCurrentAnimationFrame() {
        switch (direction) {
            case 0:
                return animDown.getCurrentFrame();
            case 1:
                return animUp.getCurrentFrame();
            case 2:
                return animRight.getCurrentFrame();
            case 3:
                return animLeft.getCurrentFrame();
            default:
                return animDown.getCurrentFrame();
        }
    }


    public Location getLocation() {
        return new Location(API.getWorld(), getX(), getY(), getDirection());
    }
}