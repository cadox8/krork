package net.athonedevs.krork.runnable;

import lombok.Getter;
import net.athonedevs.krork.Krork;

import java.util.Random;

public abstract class KrorkRunnable {

    @Getter private final int taskID;

    private KrorkRunnable() {
        taskID = randomID();
        Krork.getRunnables().add(this);
    }

    public abstract void run();


    private int randomID() {
        final int id = new Random().nextInt();
        return exists(id) ? randomID() : id;
    }
    private boolean exists(int id) {
        return Krork.getRunnables().stream().anyMatch(r -> r.getTaskID() == id);
    }
}
