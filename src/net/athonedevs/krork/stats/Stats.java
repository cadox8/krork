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

package net.athonedevs.krork.stats;

public abstract class Stats {

    private int kills = 0;
    private int deaths = 0;

    private float timePlayer = 0;
    private float firstRun = System.currentTimeMillis();

    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public float getTimePlayer() {
        return this.timePlayer;
    }

    public float getFirstRun() {
        return this.firstRun;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setTimePlayer(float timePlayer) {
        this.timePlayer = timePlayer;
    }

    public void setFirstRun(float firstRun) {
        this.firstRun = firstRun;
    }
}
