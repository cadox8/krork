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
import java.util.List;

public class PathFinder {

    private KrorkAPI api;
    private int width, height;

    public PathFinder(KrorkAPI api) {
        this.api = api;
        this.width = api.getWorld().getWidth();
        this.height = api.getWorld().getHeight();
    }

    public static boolean canCutCorners = false;

    private static Node end;
    private static int[][] gScore;
    private static int[][] hScore;
    private static int[][] fScore;
    private static Node[][] cameFrom;

    public Node toNode(int i, int j) {
        return new Node(i, j);
    }

    public List<Node> generate(int startX, int startY, int endX, int endY) {
        return generate(toNode(startX / 64, startY / 64), toNode(endX / 64, endY / 64));
    }

    public List<Node> generate(Node start, Node finish) {
        List<Node> openNodes = new ArrayList<>();
        List<Node> closedNodes = new ArrayList<>();
        end = finish;
        gScore = new int[width][height];
        fScore = new int[width][height];
        hScore = new int[width][height];
        cameFrom = new Node[width][height];
        openNodes.add(start);
        gScore[start.x][start.y] = 0;
        hScore[start.x][start.y] = calculateHeuristic(start);
        fScore[start.x][start.y] = hScore[start.x][start.y];

        while(openNodes.size() > 0) {
            Node current = getLowestNodeIn(openNodes);
            if(current == null) break;
            if(current.equals(end)) return reconstructPath(current);
            System.out.println(current.x + ", " + current.y);

            openNodes.remove(current);
            closedNodes.add(current);

            List<Node> neighbors = getNeighborNodes(current);
            for(Node n : neighbors) {

                if(closedNodes.contains(n)) continue;

                int tempGscore = gScore[current.x][current.y] + distanceBetween(n, current);

                boolean proceed = false;
                if(!openNodes.contains(n)) {
                    openNodes.add(n);
                    proceed = true;
                }
                else if(tempGscore < gScore[n.x][n.y]) proceed = true;

                if(proceed) {
                    cameFrom[n.x][n.y] = current;
                    gScore[n.x][n.y] = tempGscore;
                    hScore[n.x][n.y] = calculateHeuristic(n);
                    fScore[n.x][n.y] = gScore[n.x][n.y] + hScore[n.x][n.y];
                }
            }
        }
        return new ArrayList<>();
    }

    private List<Node> reconstructPath(Node n) {
        if(cameFrom[n.x][n.y] != null) {
            List<Node> path = reconstructPath(cameFrom[n.x][n.y]);
            path.add(n);
            return path;
        }
        else {
            List<Node> path = new ArrayList<>();
            path.add(n);
            return path;
        }
    }

    private List<Node> getNeighborNodes(Node n) {
        List<Node> found = new ArrayList<>();
        if(!isSolid(n.x + 1, n.y)) found.add(toNode(n.x + 1, n.y));
        if(!isSolid(n.x - 1, n.y)) found.add(toNode(n.x - 1, n.y));
        if(!isSolid(n.x, n.y + 1)) found.add(toNode(n.x, n.y + 1));
        if(!isSolid(n.x, n.y - 1)) found.add(toNode(n.x, n.y - 1));
        if(!isSolid(n.x + 1, n.y + 1) && (!isSolid(n.x + 1, n.y) && !isSolid(n.x, n.y + 1))) found.add(toNode(n.x + 1, n.y + 1));
        if(!isSolid(n.x - 1, n.y + 1) && (!isSolid(n.x - 1, n.y) && !isSolid(n.x, n.y + 1))) found.add(toNode(n.x - 1, n.y + 1));
        if(!isSolid(n.x - 1, n.y - 1) && (!isSolid(n.x - 1, n.y) && !isSolid(n.x, n.y - 1))) found.add(toNode(n.x - 1, n.y - 1));
        if(!isSolid(n.x + 1, n.y - 1) && (!isSolid(n.x + 1, n.y) && !isSolid(n.x, n.y - 1))) found.add(toNode(n.x + 1, n.y - 1));
        return found;
    }

    private Node getLowestNodeIn(List<Node> nodes) {
        int lowest = -1;
        Node found = null;
        for(Node n : nodes) {
            int dist = cameFrom[n.x][n.y] == null ? -1 : gScore[cameFrom[n.x][n.y].x][cameFrom[n.x][n.y].y] + distanceBetween(n, cameFrom[n.x][n.y]) + calculateHeuristic(n);
            if(dist <= lowest || lowest == -1) {
                lowest = dist;
                found = n;
            }
        }
        return found;
    }

    private int distanceBetween(Node n1, Node n2) {
        return (int) Math.round(10 * Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2)));
    }

    private int calculateHeuristic(Node start) {
        return 10 * (Math.abs(start.x - end.x) + Math.abs(start.y - end.y));
    }

    private boolean isSolid(int x, int y) {
        return api.getWorld().getTile(x, y).isSolid();
    }
}
