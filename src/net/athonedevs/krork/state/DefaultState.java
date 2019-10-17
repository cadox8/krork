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
import net.athonedevs.krork.nysvaui.components.UIButton;
import net.athonedevs.krork.nysvaui.components.UIRainbowBlock;
import net.athonedevs.krork.nysvaui.components.UIText;
import net.athonedevs.krork.nysvaui.helpers.NysvaColor;
import net.athonedevs.krork.nysvaui.helpers.RelativeDimension;

import java.awt.*;

public class DefaultState extends State {

    private NysvaManager uiManager;

    public DefaultState(KrorkAPI api) {
        super(api);

        uiManager = new NysvaManager();
        API.getMouseManager().setUiManager(uiManager);

        final UIRainbowBlock block = new UIRainbowBlock(api, 0.5);
        block.setRounded(true);
        block.setDraggable(true);
        block.setRelativeDimension(new RelativeDimension(20, 20, 300, 300));

        final UIButton button = new UIButton(api, () -> {
            ((UIRainbowBlock)uiManager.getObject(block.getComponentID())).setRounded(!((UIRainbowBlock)uiManager.getObject(block.getComponentID())).isRounded());
        });
        button.setText("Alterna los bordes!");
        button.setRelativeDimension(new RelativeDimension(350, 145, 120, 20));

        final UIText info = new UIText(api);
        info.setText("Krork Engine " + Krork.getVersion() + " by AthoneDevs");
        info.setTextColor(NysvaColor.DARK_GRAY);
        info.customizeFont(Font.BOLD, 36);
        info.setRelativeDimension(new RelativeDimension(API.getWidth() / 3, 10, 300, 300));

        uiManager.addObject(block);
        uiManager.addObject(button);
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
