package net.athonedevs.krork.state;

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.api.KrorkAPI;

import java.awt.*;

public abstract class State {

    protected KrorkAPI API;

    @Getter @Setter private static State state = null;

    public State(KrorkAPI API) {
        this.API = API;
    }


    public abstract void tick();
    public abstract void render(Graphics g);
}
