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

package net.athonedevs.krork.options;

public abstract class Options {

    /**
     * Adjust the volume of the game
     */
    private float volume = 1;

    /**
     * Adjust the windows type
     *
     * 0 => Fullscreen
     * 1 => Normal Screen
     * 2  => Borderless Screen
     */
    private int windowType = 1;

    public float getVolume() {
        return this.volume;
    }

    public int getWindowType() {
        return this.windowType;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setWindowType(int windowType) {
        this.windowType = windowType;
    }
}
