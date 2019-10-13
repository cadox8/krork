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

package net.athonedevs.krork.animation;

import java.awt.image.BufferedImage;

public class Animation {

    protected int speed, index;
    protected long lastTime, timer;

    protected BufferedImage[] frames;

    protected boolean withEnd;
    protected boolean end;

    /**
     * Creates an animation with custom speed from the given frames
     *
     * @param speed The speed to be reproduced
     * @param frames The Images from the animation
     */
    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
        withEnd(false);
        end = false;
    }

    public void tick() {
        if (end) return;
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length) {
                index = 0;
                if (withEnd) end = true;
            }
        }
    }

    /**
     * Returns the current image at the point
     *
     * @return The image in the frame
     */
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    public String toString() {
        return "Animation(speed=" + this.speed + ", index=" + this.index + ", lastTime=" + this.lastTime + ", timer=" + this.timer + ", frames=" + java.util.Arrays.deepToString(this.frames) + ")";
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getIndex() {
        return this.index;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage[] getFrames() {
        return frames;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public Animation withEnd(boolean end) {
        this.withEnd = true;
        return this;
    }
}
