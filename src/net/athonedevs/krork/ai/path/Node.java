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
import lombok.Setter;

public class Node {

    @Getter @Setter public int x, y;
    @Getter @Setter public int h; //heuristic distance from endNode
    @Getter @Setter public int g; //distance from startNode
    @Getter @Setter public int f; //h+g
    @Getter @Setter public boolean isBlock; //path blocker?
    @Getter @Setter public Node parent; //the parent node. if algorithm founds a path it can trace back

    public Node(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public void calculateH(Node endNode) {
        //this.h = Math.abs(endNode.yy - this.yy) + Math.abs(endNode.xx - this.xx);
        int dstX = Math.abs(this.getX() - endNode.getX());
        int dstY = Math.abs(this.getY() - endNode.getY());

        if (dstX > dstY) {
            this.h = 14*dstY + 10* (dstX-dstY);
        } else {
            this.h = 14*dstX + 10 * (dstY-dstX);
        }
    }

    public void calculateG(Node startNode) {
        //this.g=Math.abs(startNode.yy - this.yy) + Math.abs(startNode.xx - this.xx);
        int dstX = Math.abs(this.getX() - startNode.getX());
        int dstY = Math.abs(this.getY() - startNode.getY());

        if (dstX > dstY) {
            this.g = 14*dstY + 10* (dstX-dstY);
        } else {
            this.g = 14*dstX + 10 * (dstY-dstX);
        }
    }

    public void calculateF() {
        int finalCost = getG() + getH();
        this.setF(finalCost);
    }
}
