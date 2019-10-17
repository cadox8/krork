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

package net.athonedevs.krork;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.display.Display;
import net.athonedevs.krork.input.KeyManager;
import net.athonedevs.krork.input.MouseManager;
import net.athonedevs.krork.state.DefaultState;
import net.athonedevs.krork.state.State;
import net.athonedevs.krork.utils.Colors;
import net.athonedevs.krork.utils.GameCamera;
import net.athonedevs.krork.utils.Log;
import net.athonedevs.krork.utils.Updater;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Krork implements Runnable {

    // Info
    private static final String version = "v2 Beta";
    private static final int versionNumber = 19;
    private static String game;
    //

    private Display display;
    private int width, height;
    private String title;

    private boolean running = false;
    private Thread thread;

    private MouseManager mouseManager;
    private KeyManager keyManager;

    // Camera
    public GameCamera gameCamera;

    // API
    private KrorkAPI API;

    /**
     * The default constructor.
     *
     * @param game Game name
     * @param title Windows title
     * @param size Game Size (Dimension)
     */
    public Krork(String game, String title, Dimension size) {
        this(game, title, (int)size.getWidth(), (int)size.getHeight());
    }

    /**
     * The default constructor.
     *
     * @param game Game name
     * @param title Windows title
     * @param width Game width
     * @param height Game Height
     */
    public Krork(String game, String title, int width, int height) {
        setGame(game);
        this.width = width;
        this.height = height;
        this.title = title;

        API = new KrorkAPI(this);

        mouseManager = new MouseManager();
        keyManager = new KeyManager(API);
        init();
    }

    public static String getVersion() {
        return Krork.version;
    }
    public static int getVersionNumber() {
        return Krork.versionNumber;
    }

    public static String getGame() {
        return Krork.game;
    }

    public static void setGame(String game) {
        Krork.game = game;
    }

    private void init() {
        display = new Display(title, getWidth(), getHeight());

        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseWheelListener(mouseManager);

        gameCamera = new GameCamera(API, 0, 0);

        State.setState(new DefaultState(API));

        // Version info
        KrorkAPI.enableDebug(true);

        Log.log(Log.LogType.SUCCESS, "Krork Engine is now working", "Krork");
        Log.log(Log.LogType.NORMAL, "Krork Version: " + Colors.RED.getColor() + getVersion() + "\n", "Krork");
        Log.log(Log.LogType.NORMAL, "Checking for updates...", "Krork");
        Updater.checkForUpdate();
    }

    private void tick() {
        if (State.getState() != null) State.getState().tick();
    }

    private void render() {
        final BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        final Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (State.getState() != null) State.getState().render(g);

        bs.show();
        g.dispose();
    }

    public void run() {
        int fps = 60;
        double timePerTick = (1000000000D / fps);
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Display getDisplay() {
        return this.display;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isRunning() {
        return this.running;
    }

    public MouseManager getMouseManager() {
        return this.mouseManager;
    }

    public KeyManager getKeyManager() {
        return this.keyManager;
    }

    public GameCamera getGameCamera() {
        return this.gameCamera;
    }

    public KrorkAPI getAPI() {
        return this.API;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
