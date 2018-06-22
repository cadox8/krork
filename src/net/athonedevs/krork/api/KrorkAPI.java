package net.athonedevs.krork.api;


import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.Krork;
import net.athonedevs.krork.utils.GameCamera;
import net.athonedevs.krork.world.World;

public class KrorkAPI {

    @Getter @Setter private static boolean debugEnabled = false;


    @Getter @Setter private Krork game;
    @Getter @Setter private World world;


    public KrorkAPI(Krork game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }


    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }
}
