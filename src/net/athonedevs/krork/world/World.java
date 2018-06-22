package net.athonedevs.krork.world;

import lombok.Getter;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.utils.Utils;

import java.awt.*;

public class World {

    private KrorkAPI API;

    @Getter private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;


    private String path;

    public World(KrorkAPI API, String path) {
        this.API = API;
        this.path = path;

        addEntities();
        loadWorld();
    }


    public void addEntities(){

    }

    public void tick(){

    }

    public void render(Graphics g){

    }

    // Loaded from Text File (Should we change it to get Entities from same site?)
    private void loadWorld() {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    private String worldName() {
        return path.split("/")[2].split("\\.")[0];
    }
    @Override
    public String toString() {
        return "World{Name:" + worldName() + "}";
    }
}
