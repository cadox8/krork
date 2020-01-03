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

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.entities.Entity;
import net.athonedevs.krork.entities.EntityManager;
import net.athonedevs.krork.tiles.Tile;
import net.athonedevs.krork.tiles.TileManager;
import net.athonedevs.krork.utils.Utils;

import java.awt.*;
import java.util.Arrays;

public class World {

    private KrorkAPI krorkAPI;

    private int width, height;
    private int playerX, playerY;
    private TileData[][] tiles;

    //Entities
    private EntityManager entityManager;

    private final String path;

    /**
     * Default world constructor
     *
     * @param krorkAPI The KrorkAPI Class
     * @param path The path to the world
     */
    public World(KrorkAPI krorkAPI, String path) {
        this.krorkAPI = krorkAPI;
        this.path = path;
        this.entityManager = new EntityManager(krorkAPI);

        loadWorld();
    }

    /**
     * Method to load entities in world.
     * You can save the entities and then add them, or add new entities each time.
     *
     * @param entities An array of entities
     */
    public void addEntities(Entity... entities) {
        Arrays.asList(entities).forEach(entityManager::addEntity);
    }

    public synchronized void tick(){
        entityManager.tick();
    }

    public synchronized void render(Graphics g){
        int xStart = (int) Math.max(0, krorkAPI.getGameCamera().getXOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (krorkAPI.getGameCamera().getXOffset() + krorkAPI.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, krorkAPI.getGameCamera().getYOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (krorkAPI.getGameCamera().getYOffset() + krorkAPI.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - krorkAPI.getGameCamera().getXOffset()), (int) (y * Tile.TILEHEIGHT - krorkAPI.getGameCamera().getYOffset()));
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

        tiles = new TileData[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tokens[(x + y * width) + 4].contains(":")) {
                    final String[] parts = tokens[(x + y * width) + 4].split(":");
                    tiles[x][y] = new TileData(Utils.parseInt(parts[0]), Utils.parseInt(parts[1]));
                } else {
                    tiles[x][y] = new TileData(Utils.parseInt(tokens[(x + y * width) + 4]), 0);
                }
            }
        }
    }

    public String getWorldName() {
        return path.split("/")[2].split("\\.")[0];
    }
    @Override
    public String toString() {
        return "World{Name:" + getWorldName() + " Entities: " + entityManager.getEntities().toString() + "}";
    }

    public KrorkAPI getAPI() {
        return this.krorkAPI;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getPlayerX() {
        return this.playerX;
    }

    public int getPlayerY() {
        return this.playerY;
    }

    public TileData[][] getTiles() {
        return this.tiles;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    private static class TileData {
        private int id;
        private int subid;

        public TileData(int id, int subid) {
            this.id = id;
            this.subid = subid;
        }

        public int getId() {
            return this.id;
        }

        public int getSubid() {
            return this.subid;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSubid(int subid) {
            this.subid = subid;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof TileData)) return false;
            final TileData other = (TileData) o;
            if (!other.canEqual((Object) this)) return false;
            if (this.getId() != other.getId()) return false;
            if (this.getSubid() != other.getSubid()) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof TileData;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            result = result * PRIME + this.getId();
            result = result * PRIME + this.getSubid();
            return result;
        }

        public String toString() {
            return "World.TileData(id=" + this.getId() + ", subid=" + this.getSubid() + ")";
        }
    }
}
