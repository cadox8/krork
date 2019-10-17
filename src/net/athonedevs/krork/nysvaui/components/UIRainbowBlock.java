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

package net.athonedevs.krork.nysvaui.components;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.helpers.NysvaUtils;

public class UIRainbowBlock extends UIBlock {

    private double speed;
    private long lastTime, timer = 0;

    public UIRainbowBlock(KrorkAPI api, double speedInSeconds) {
        super(api);

        this.speed = 1000 * speedInSeconds;
    }

    @Override
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            timer = 0;
            setBackground(NysvaUtils.randomColor());
        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
