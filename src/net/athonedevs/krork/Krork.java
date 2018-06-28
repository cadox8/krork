package net.athonedevs.krork;

import lombok.Getter;
import lombok.Setter;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.display.Display;
import net.athonedevs.krork.state.MenuState;
import net.athonedevs.krork.state.State;
import net.athonedevs.krork.utils.GameCamera;
import net.athonedevs.krork.utils.Log;
import net.athonedevs.krork.utils.Updater;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Krork implements Runnable {

    // Info
    @Getter private static final String version = "0.4.5 Alpha";

    //


    @Getter private Display display;
    @Getter private int width, height;
    private String title;


    @Getter @Setter private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    // States (Don't Use)
    public State menuState;


    // Camera
    @Getter public GameCamera gameCamera;


    // API
    @Getter private KrorkAPI API;


    public Krork(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        display = new Display(title, width, height);

        API = new KrorkAPI(this);
        gameCamera = new GameCamera(API, 0, 0);

        menuState = new MenuState(API);

        State.setState(menuState);

        // Version info
        KrorkAPI.setDebugEnabled(true);

        Log.log(Log.LogType.SUCCESS, "Krork Engine is now working");
        Log.log(Log.LogType.NORMAL, "Version: \u001B[31m" + getVersion() + "\n");
        Log.log(Log.LogType.NORMAL, "Checking for updates...");
        Updater.checkForUpdate();
    }

    private void tick() {
        if (State.getState() != null) State.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (State.getState() != null) State.getState().render(g);

        bs.show();
        g.dispose();
    }


    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
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
