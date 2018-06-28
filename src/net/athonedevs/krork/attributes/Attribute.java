package net.athonedevs.krork.attributes;

import lombok.Getter;
import net.athonedevs.krork.api.KrorkAPI;

public abstract class Attribute {

    protected final KrorkAPI API;
    @Getter private final int id;
    @Getter private final String name;

    public Attribute(KrorkAPI API, int id, String name) {
        this.API = API;
        this.id = id;
        this.name = name;
    }

    public abstract void perform();
}
