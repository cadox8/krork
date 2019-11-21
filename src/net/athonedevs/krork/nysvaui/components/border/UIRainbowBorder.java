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

package net.athonedevs.krork.nysvaui.components.border;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.helpers.NysvaColor;
import net.athonedevs.krork.nysvaui.helpers.NysvaUtils;

import java.util.Arrays;
import java.util.List;

public class UIRainbowBorder extends UIBorder {

    private double speed;
    private long lastTime, timer = 0;

    private List<NysvaColor> colors;
    private int alpha = 255;

    public UIRainbowBorder(KrorkAPI api, int borderSize, double speedInSeconds) {
        super(api, borderSize);
        this.speed = 1000 * speedInSeconds;
        colors = Arrays.asList(NysvaColor.allColors());
    }

    @Override
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            timer = 0;
            setBackground(finalColor(NysvaUtils.randomColor(colors)));
        }
    }

    public void transparentBackground(int alpha) {
        this.alpha = alpha;
        setBackground(getBackground().transparent(alpha));
    }

    public void rainbowColors(NysvaColor... colors) {
        this.colors.addAll(Arrays.asList(colors));
    }

    private NysvaColor finalColor(NysvaColor color) {
        return color.transparent(alpha);
    }
}
