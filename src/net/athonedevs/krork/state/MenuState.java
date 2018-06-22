package net.athonedevs.krork.state;

import net.athonedevs.krork.Krork;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.ui.UIManager;

import java.awt.*;

@Deprecated
public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(KrorkAPI API) {
        super(API);

        uiManager = new UIManager(API);
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
        g.setColor(Color.WHITE);
        g.drawString("Version: " + Krork.getVersion(), 5, 595);
        g.drawString("© AthoneDevs", 505, 595);
    }
}
