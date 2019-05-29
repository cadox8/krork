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

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.display.Display;
import net.athonedevs.krork.input.KeyManager;
import net.athonedevs.krork.input.MouseManager;
import net.athonedevs.krork.state.DefaultState;
import net.athonedevs.krork.state.State;
import net.athonedevs.krork.utils.GameCamera;
import net.athonedevs.krork.utils.Log;
import net.athonedevs.krork.utils.Updater;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Krork implements Runnable {

    // Info
    @Getter private static final String version = "v0.9 Alpha";
    @Getter @Setter private static String game;
    //


    @Getter private Display display;
    @Getter @Setter private int width, height;
    private String title;


    @Getter @Setter private boolean running = false;
    private Thread thread;

    @Getter private MouseManager mouseManager;
    @Getter private KeyManager keyManager;

    // Camera
    @Getter public GameCamera gameCamera;

    // API
    @Getter private KrorkAPI API;

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
        KrorkAPI.setDebugEnabled(true);

        Log.log(Log.LogType.SUCCESS, "Krork Engine is now working");
        Log.log(Log.LogType.NORMAL, "Krork Version: \u001B[31m" + getVersion() + "\n");
        Log.log(Log.LogType.NORMAL, "Checking for updates...");
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
        double timePerTick = (double)(1000000000 / fps);
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
}
