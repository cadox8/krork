package net.athonedevs.krork.saves;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class EntityData {

    private int entityID;
    private String entityData;
    private LocationUtils location;

    @Data
    public class LocationUtils {

        private String world;
        private float x;
        private float y;
        private int direction;
    }
}
