package net.athonedevs.krork.entities.creatures;

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.Entity;

import java.awt.*;
import java.util.Arrays;


public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;

    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

    @Getter @Setter protected float speed;
    @Getter @Setter protected float xMove, yMove;

    @Getter @Setter protected boolean freeze = false;


    public Creature(KrorkAPI API, float x, float y, int width, int height) {
        super(API, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void fixAnimations() {
        if (animations[0] != null) Arrays.asList(animations).forEach(a -> a.setSpeed((int)(speed * 166.66)));
    }


    @Override
    public void specialRender(Graphics g) {}
}
