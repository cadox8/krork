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

package net.athonedevs.krork.options;

import lombok.NonNull;

import java.lang.reflect.Field;

public class OptionsManager {

    private final Options options;

    public OptionsManager(@NonNull Options options) {
        this.options = options;
    }

    public void adjustValue(String valueName, Object newValue) throws Exception {
        final Field f = getField(valueName);
        f.setAccessible(true);
        f.set(getValue(valueName), newValue);
        f.setAccessible(false);
    }

    public Object getValue(String valueName) throws Exception {
        return getField(valueName).get(options);
    }

    private Field getField(String valueName) throws Exception {
        return options.getClass().getField(valueName);
    }

    public Options getOptions() {
        return options;
    }
}
