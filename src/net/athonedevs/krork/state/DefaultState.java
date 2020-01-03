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

package net.athonedevs.krork.state;

import net.athonedevs.krork.Krork;
import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.NysvaManager;
import net.athonedevs.krork.nysvaui.components.base.UIBlock;
import net.athonedevs.krork.nysvaui.components.text.UIText;
import net.athonedevs.krork.nysvaui.helpers.NysvaColor;
import net.athonedevs.krork.nysvaui.helpers.UIDimension;

import java.awt.*;

public class DefaultState extends State {

    private NysvaManager uiManager;

    public DefaultState(KrorkAPI krorkAPI) {
        super(krorkAPI);

        uiManager = new NysvaManager();
        API.getMouseManager().setUiManager(uiManager);

        final UIBlock block = new UIBlock(krorkAPI, NysvaColor.DARK_GRAY);
        block.setRounded(true);
        block.setDraggable(true);
        block.setUIDimension(new UIDimension(100, 100, 300, 300));

        final UIText info = new UIText(krorkAPI, "Krork Engine " + Krork.getVersion() + " by AthoneDevs");
        info.setTextColor(NysvaColor.DARK_GRAY);
        info.customizeFont(Font.BOLD, 36);
        info.setUIDimension(new UIDimension(API.getWidth() / 3, 10, 300, 300));

        final UIText i = new UIText(krorkAPI, "Hola");
        final UIDimension dimension = new UIDimension(5, 150, 300, 300);
        dimension.setRefX(5);
        dimension.setRefY(150);

        i.setUIDimension(dimension);
        block.addUIComponent(i);

        uiManager.addObject(block);
        uiManager.addObject(info);
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

    public NysvaManager getUiManager() {
        return this.uiManager;
    }
}
