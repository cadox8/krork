package net.athonedevs.krork.api;


import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.Krork;

public class KrorkAPI {

    @Getter @Setter private Krork game;

    @Getter @Setter private boolean debug = false;

    public KrorkAPI(Krork game) {
        this.game = game;
    }
}
