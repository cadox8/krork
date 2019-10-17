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
import net.athonedevs.krork.nysvaui.NysvaUtils;

public class UIRainbowBorder extends UIBorder {

    private double speed;
    private long lastTime, timer = 0;

    public UIRainbowBorder(KrorkAPI api, int borderSize, double speedInSeconds) {
        super(api, borderSize);

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
}
