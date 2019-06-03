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

import java.util.Timer;
import java.util.TimerTask;

public abstract class KrorkTask extends TimerTask implements Runnable {

    private Timer timer;

    public KrorkTask() {
       timer = new Timer();
    }

    /**
     * The run method which will execute
     */
    public abstract void run();

    /**
     * Starts the runnable after a period of time
     *
     * @param delay The delay in seconds
     * @return The KrorkTask class
     */
    public KrorkTask scheduleDelayed(int delay) {
        timer.schedule(this, delay);
        stop();
        return this;
    }

    /**
     * Starts the runnable
     *
     * @param initialDelay The initial delay before the first execution (in seconds)
     * @param period The period between the executions
     * @return The KrorkTask class
     */
    public KrorkTask schedule(int initialDelay, int period) {
        timer.scheduleAtFixedRate(this, initialDelay, period);
        return this;
    }

    /**
     * Stops the runnable
     */
    public void stop() {
        timer.cancel();
    }
}
