package net.athonedevs.krork.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.athonedevs.krork.api.KrorkAPI;

@AllArgsConstructor
public class GameCamera {

    private KrorkAPI API;
    @Getter private float xOffset, yOffset;

    // ToDo: Check offset with Tiles

}
