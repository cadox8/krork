package net.athonedevs.krork.gfx.assets;

import net.athonedevs.krork.gfx.Sprites;
import net.athonedevs.krork.utils.Log;

import java.awt.image.BufferedImage;

public class Assets {

    public final int WIDTH = 32, HEIGHT = 32;
    private Sprites sprites;

    // Bug Asset
    public BufferedImage bug;


    public Assets() {
        init();
        Log.log("Loading Assets...");
    }

    private void init() {
        bug = sprites.randomImage(WIDTH, HEIGHT);


    }

    // Utils
    private BufferedImage getImage(int x, int y) {
        return getImage(x, y, WIDTH, HEIGHT);
    }
    private BufferedImage getImage(int x, int y, int width, int height) {
        return sprites.crop(width * x, height * y, width, height);
    }
}
