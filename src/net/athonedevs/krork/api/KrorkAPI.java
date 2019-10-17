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

package net.athonedevs.krork.api;


import net.athonedevs.krork.Krork;
import net.athonedevs.krork.gfx.Fonts;
import net.athonedevs.krork.input.KeyManager;
import net.athonedevs.krork.input.MouseManager;
import net.athonedevs.krork.utils.GameCamera;
import net.athonedevs.krork.world.World;

import java.awt.*;

public class KrorkAPI {

    private static boolean debugEnabled = false;

    private Krork krork;
    private World world;

    private static Font gameFont = new Fonts("Arial", Font.PLAIN, 12).getFont();

    public KrorkAPI(Krork krork) {
        this.krork = krork;
    }

    public static boolean isDebugEnabled() {
        return debugEnabled;
    }

    public static Font getGameFont() {
        return gameFont;
    }

    public static void enableDebug(boolean debugEnabled) {
        KrorkAPI.debugEnabled = debugEnabled;
    }

    public static void setGameFont(Font gameFont) {
        KrorkAPI.gameFont = gameFont;
    }

    public KeyManager getKeyManager() {
        return getKrork().getKeyManager();
    }
    public MouseManager getMouseManager() {
        return getKrork().getMouseManager();
    }

    public GameCamera getGameCamera() {
        return krork.getGameCamera();
    }

    public int getWidth() {
        return krork.getWidth();
    }

    public int getHeight() {
        return krork.getHeight();
    }

    public Krork getKrork() {
        return this.krork;
    }

    public World getWorld() {
        return this.world;
    }

    public void setKrork(Krork krork) {
        this.krork = krork;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
