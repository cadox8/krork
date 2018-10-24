/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 */

package net.athonedevs.krork.state;

import net.athonedevs.krork.Krork;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.ui.UIManager;
import net.athonedevs.krork.ui.UIText;
import net.athonedevs.krork.utils.Log;

import java.awt.*;

public class DefaultState extends State {

    private UIManager uiManager;

    public DefaultState(KrorkAPI api) {
        super(api);

        uiManager = new UIManager(API);

        uiManager.addObject(new UIText((float)(API.getWidth() / 3), (float)(API.getHeight() / 2), Color.BLACK, "Krork Engine " + Krork.getVersion() + " by AthoneDevs", () -> Log.log("Works!")));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
