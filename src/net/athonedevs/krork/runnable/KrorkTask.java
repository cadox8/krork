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

package net.athonedevs.krork.runnable;

import lombok.Getter;
import net.athonedevs.krork.Krork;

import java.util.Random;

public abstract class KrorkTask {

    @Getter private final int taskID;

    public KrorkTask() {
        taskID = randomID();
        Krork.getRunnables().add(this);
    }

    /**
     * The run method to start the runnable
     */
    public abstract void run();


    private int randomID() {
        final int id = new Random().nextInt();
        return exists(id) ? randomID() : id;
    }
    private boolean exists(int id) {
        return Krork.getRunnables().stream().anyMatch(r -> r.getTaskID() == id);
    }
}
