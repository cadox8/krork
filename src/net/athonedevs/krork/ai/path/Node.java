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

import net.athonedevs.krork.annotations.Experimental;

@Experimental
public class Node {

    public int x;
    public int y;
    public double hValue;
    public int gValue;
    public double fValue;
    public Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
