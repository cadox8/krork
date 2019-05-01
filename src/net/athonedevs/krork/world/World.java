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

package net.athonedevs.krork.world;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.Entity;
import net.athonedevs.krork.entities.EntityManager;
import net.athonedevs.krork.tiles.Tile;
import net.athonedevs.krork.tiles.TileManager;
import net.athonedevs.krork.utils.Utils;

import java.awt.*;
import java.util.Arrays;

public class World {

    private KrorkAPI API;

    @Getter private int width, height;
    @Getter private int playerX, playerY;
    @Getter private TileData[][] tiles;
    private boolean[][] visited;

    //Entities
    @Getter private EntityManager entityManager;

    private final String path;

    /**
     * Default world constructor
     *
     * @param API The KrorkAPI Class
     * @param path The path to the world
     */
    public World(KrorkAPI API, String path) {
        this.API = API;
        this.path = path;
        this.entityManager = new EntityManager(API);

        loadWorld();
    }

    /**
     * Method to load entities in world.
     * You can save the entities and then add them, or add new entities each time.
     *
     * @param entities An array of entities
     */
    public void addEntities(Entity... entities){
        Arrays.asList(entities).forEach(entityManager::addEntity);
    }

    public void tick(){
        entityManager.tick();
    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, API.getGameCamera().getXOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (API.getGameCamera().getXOffset() + API.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, API.getGameCamera().getYOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (API.getGameCamera().getYOffset() + API.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - API.getGameCamera().getXOffset()), (int) (y * Tile.TILEHEIGHT - API.getGameCamera().getYOffset()));
            }
        }
        //Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.bug;
        return TileManager.getTile(tiles[x][y].getId(), tiles[x][y].getSubid());
    }

    private void loadWorld() {
        final String file = Utils.loadFileAsString(path);
        final String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        playerX = Utils.parseInt(tokens[2]);
        playerY = Utils.parseInt(tokens[3]);

        visited = new boolean[width][height];
        tiles = new TileData[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final TileData data;
                if (tokens[(x + y * width) + 4].contains(":")) {
                    final String[] parts = tokens[(x + y * width) + 4].split(":");
                    data = new TileData(Utils.parseInt(parts[0]), Utils.parseInt(parts[1]));
                } else {
                    data = new TileData(Utils.parseInt(tokens[(x + y * width) + 4]), 0);
                }
                tiles[x][y] = data;
            }
        }
    }

    public String worldName() {
        return path.split("/")[2].split("\\.")[0];
    }
    @Override
    public String toString() {
        return "World{Name:" + worldName() + "}";
    }

    @AllArgsConstructor
    @Data
    private class TileData {
        private int id;
        private int subid;
    }
}
