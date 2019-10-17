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

package net.athonedevs.krork.nysvaui;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NysvaUtils {

    private static Random r = new Random();
    private static NysvaColor lastColor = null;

    public static NysvaColor randomColor() {
        return randomColor(Arrays.asList(NysvaColor.DARK_GRAY, NysvaColor.BLUE, NysvaColor.GREEN, NysvaColor.ORANGE, NysvaColor.PURPLE, NysvaColor.RED, NysvaColor.TURQUOISE, NysvaColor.WHITE,
                NysvaColor.YELLOW));
    }

    public static NysvaColor randomColor(List<NysvaColor> selectedColors) {
        final NysvaColor color = selectedColors.get(r.nextInt(selectedColors.size()));
        if (lastColor != null && color.getRGB() == lastColor.getRGB()) return randomColor(selectedColors);
        return color;
    }
}
