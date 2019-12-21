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

package net.athonedevs.krork.nysvaui.components.text;

import net.athonedevs.krork.api.KrorkAPI;
import net.athonedevs.krork.nysvaui.ClickListener;

public class UITextButton extends UIText {

    private ClickListener clicker;

    public UITextButton(KrorkAPI krorkAPI, String text, ClickListener clicker) {
        super(krorkAPI, text);

        this.clicker = clicker;
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
