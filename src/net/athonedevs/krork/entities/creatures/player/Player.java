/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 */

package net.athonedevs.krork.entities.creatures.player;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.Entity;
import net.athonedevs.krork.entities.creatures.Creature;
import net.athonedevs.krork.utils.Log;

import java.awt.*;

/**
 * Default Player class (Must be created in game)
 */
@Deprecated
public class Player extends Creature {

    public Player(KrorkAPI API, float x, float y) {
        super(API, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 20;
        bounds.y = 44;
        bounds.width = 25;
        bounds.height = 19;

        setHealth(10);

        setDamage(DEFAULT_DAMAGE);
        setArmor(DEFAULT_ARMOR);

        setAttackCooldown(300);

        animations[0] = animDown;
        animations[1] = animUp;
        animations[2] = animLeft;
        animations[3] = animRight;
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            if (KrorkAPI.isDebugEnabled()) Log.log("Attack in cooldown: " + attackTimer + "/" + attackCooldown);
            return;
        }

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        switch (getDirection()) {
            case 0:
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;
                break;
            case 1:
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
                break;
            case 2:
                ar.x = cb.x - arSize;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                break;
            case 3:
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                break;
        }

        attackTimer = 0;

        for (Entity e : API.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(this);
                return;
            }
        }
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void die() {

    }
}
