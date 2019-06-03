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

package net.athonedevs.krork.gfx;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {

    private final String name;
    private int type = Font.PLAIN;
    private int size = 14;

    /**
     * Creates a new font from a name
     *
     * @param name The font name
     */
    public Fonts(String name) {
        this.name = name;
    }

    /**
     * Creates a new font
     *
     * @see Font
     *
     * @param name The font name
     * @param type The font type
     * @param size The font size
     */
    public Fonts(String name, int type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    /**
     * Generates the font
     *
     * @return Returns the font
     */
    public Font getFont() {
        return new Font(name, type, size);
    }

    /**
     * Generates a custom font from a file
     *
     * @param path The path where the file is (in .ttf format)
     * @return The new font generated
     */
    public Font newFont(String path) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path + File.pathSeparator + name + ".ttf")).deriveFont(type, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
