/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 */

package net.athonedevs.krork.display;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.utils.SizeUtils;

import java.awt.*;

@AllArgsConstructor
public class Resize {

    private KrorkAPI api;
    private int width, height;

    private final Dimension DEFAULT_RESOLUTION = new Dimension(1920, 1080);
    private final Dimension LOW_RESOLUTION = new Dimension(1366, 768);

    @Getter private static double SCALE = 1;

    public void adjustScreenSize() {
        final Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension d = kit.getScreenSize();

        if (d.getWidth() >= DEFAULT_RESOLUTION.getWidth() && d.getHeight() >= DEFAULT_RESOLUTION.getHeight()) return;
        SCALE = 1.2;

        api.getGame().setWidth((int)(width / SCALE));
        api.getGame().setHeight((int)(height / SCALE));
    }


    public static SizeUtils resize(int x, int y) {
        return resize((int)(x / SCALE), (int)(y / SCALE), 0, 0);
    }
    public static SizeUtils resize(int x, int y, int width, int height) {
        return new SizeUtils((int)(x / SCALE), (int)(y / SCALE), (int)(width / SCALE), (int)(height / SCALE));
    }
}
