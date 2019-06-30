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

public class Node {

    public int x, y;
    public int h; //heuristic distance from endNode
    public int g; //distance from startNode
    public int f; //h+g
    public boolean isBlock; //path blocker?
    public Node parent; //the parent node. if algorithm founds a path it can trace back

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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getH() {
        return this.h;
    }

    public int getG() {
        return this.g;
    }

    public int getF() {
        return this.f;
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
