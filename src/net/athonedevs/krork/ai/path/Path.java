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

package net.athonedevs.krork.ai.path;

import lombok.Getter;

import java.util.ArrayList;

public class Path {

    private ArrayList<Step> steps = new ArrayList<>();

    public Path() {}

    /**
     * Get the length of the path, i.e. the number of steps
     *
     * @return The number of steps in this path
     */
    public int getLength() {
        return steps.size();
    }

    /**
     * Get the step at a given index in the path
     *
     * @param index The index of the step to retrieve. Note this should
     * be >= 0 and < getLength();
     * @return The step information, the position on the map.
     */
    public Step getStep(int index) {
        return steps.get(index);
    }

    /**
     * Get the x coordinate for the step at the given index
     *
     * @param index The index of the step whose x coordinate should be retrieved
     * @return The x coordinate at the step
     */
    public int getX(int index) {
        return getStep(index).x;
    }

    /**
     * Get the y coordinate for the step at the given index
     *
     * @param index The index of the step whose y coordinate should be retrieved
     * @return The y coordinate at the step
     */
    public int getY(int index) {
        return getStep(index).y;
    }

    /**
     * Append a step to the path.
     *
     * @param x The x coordinate of the new step
     * @param y The y coordinate of the new step
     */
    public void appendStep(int x, int y) {
        steps.add(new Step(x,y));
    }

    /**
     * Prepend a step to the path.
     *
     * @param x The x coordinate of the new step
     * @param y The y coordinate of the new step
     */
    public void prependStep(int x, int y) {
        steps.add(0, new Step(x, y));
    }

    public boolean contains(int x, int y) {
        return steps.contains(new Step(x,y));
    }

    public class Step {
        @Getter private int x;
        @Getter private int y;

        public Step(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int hashCode() {
            return x*y;
        }


        public boolean equals(Object other) {
            if (other instanceof Step) {
                Step o = (Step) other;
                return (o.x == x) && (o.y == y);
            }
            return false;
        }
    }
}
