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

import net.athonedevs.krork.api.KrorkAPI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {

    private final KrorkAPI api;

    public Node[][] searchArea;
    public PriorityQueue<Node> openList;
    public ArrayList<Node> closedList;
    public ArrayList<Node> neighbourList;
    public Node startNode;
    public Node endNode;
    public Node currentNode;
    public Node parentNode;

    private int width, height;

    public AStar(KrorkAPI api, int startNodeX,int startNodeY, int endNodeX,int endNodeY) {
        this.api = api;
        width = api.getWorld().getWidth()*64;
        height = api.getWorld().getHeight()*64;
        this.searchArea = new Node[width][height];
        this.startNode=searchArea[startNodeX][startNodeY];
        this.endNode=searchArea[endNodeX][endNodeY];
        this.openList = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Integer.compare(node0.getF(), node1.getF());
            }
        });
        this.closedList = new ArrayList<>();
        this.neighbourList = new ArrayList<>();
    }


    public ArrayList<Node> findPath() {
        ArrayList<Node> path = new ArrayList<>();
        int tentativeG;

        startNode.setG(0);
        startNode.calculateH(endNode);
        startNode.calculateF();
        openList.add(startNode);

        while(!openList.isEmpty()) {

            currentNode=openList.poll();
            closedList.add(currentNode);

            if (currentNode.getX()==endNode.getX() && currentNode.getY()==endNode.getY()) {
                //path found!
                path=getPath(currentNode);
                System.out.println("Path found!");
                return path;
            } else {
                //search neighbours
                //neighbours:
                //(x-1,y-1)     (x0,y-1)    (x+1,y-1)
                //(x-1,y0)      (x0,y0)     (x+1,y0)
                //(x-1,y+1)     (x0,y+1)    (x+1,y+1)
                if (!neighbourList.isEmpty()) neighbourList.clear();
                //+map bounds check
                //upper row---------------------------------------------------------------------------------------------------------------------------------------
                if (currentNode.getX() > 0 && currentNode.getY() > 0)                   neighbourList.add(searchArea[currentNode.getX()-1][currentNode.getY()-1]);
                if (currentNode.getY() > 0)                                             neighbourList.add(searchArea[currentNode.getX()][currentNode.getY()-1]);
                if (currentNode.getX() < width && currentNode.getY() > 0)          neighbourList.add(searchArea[currentNode.getX()+1][currentNode.getY()-1]);
                //middle row--------------------------------------------------------------------------------------------------------------------------------------
                if (currentNode.getX() > 0)                                             neighbourList.add(searchArea[currentNode.getX()-1][currentNode.getY()]);
                if (currentNode.getX() < width)                                    neighbourList.add(searchArea[currentNode.getX()+1][currentNode.getY()]);
                //lower row---------------------------------------------------------------------------------------------------------------------------------------
                if (currentNode.getX() > 0 && currentNode.getY() < width)          neighbourList.add(searchArea[currentNode.getX()-1][currentNode.getY()+1]);
                if (currentNode.getY() < width)                                    neighbourList.add(searchArea[currentNode.getX()][currentNode.getY()+1]);
                if (currentNode.getX() < width && currentNode.getY() < width) neighbourList.add(searchArea[currentNode.getX()+1][currentNode.getY()+1]);

                for (Node neighbour : neighbourList) {
                    if (neighbour.isBlock) continue;
                    currentNode.calculateG(startNode);
                    tentativeG=currentNode.getG()+calculateNodeDist(currentNode,neighbour);

                    if (!openList.contains(neighbour) && !closedList.contains(neighbour) || tentativeG < neighbour.getG()) {
                        neighbour.setParent(currentNode);
                        neighbour.setG(tentativeG);
                        neighbour.calculateH(endNode);
                        neighbour.calculateF();
                        if (closedList.contains(neighbour)) closedList.remove(neighbour);
                        if (!openList.contains(neighbour)) openList.add(neighbour);
                    }
                }
            }
        }
        return path;
    }

    public ArrayList<Node> getPath(Node currentNode) {
        ArrayList<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parentNode;
        while ((parentNode = currentNode.getParent()) != null && parentNode!=startNode) {
            path.add(0, parentNode);
            currentNode = parentNode;
        }
        return path;
    }

    public int calculateNodeDist(Node node1,Node node2) {
        //this.g=Math.abs(startNode.yy - this.yy) + Math.abs(startNode.xx - this.xx);
        int dst;
        int dstX = Math.abs(node1.getX() - node2.getX());
        int dstY = Math.abs(node1.getY() - node2.getY());

        if (dstX > dstY) {
            dst = 14*dstY + 10* (dstX-dstY);
        } else {
            dst = 14*dstX + 10 * (dstY-dstX);
        }
        return dst;
    }
}
