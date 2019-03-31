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

package net.athonedevs.krork.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.athonedevs.krork.Krork;
import net.athonedevs.krork.api.KrorkAPI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Log {

    @AllArgsConstructor
    public enum LogType {
        SUCCESS("[Success] ", "\u001B[32m"),
        NORMAL("", ""),
        WARNING("[Warning] ", "\u001B[33m"),
        DANGER("[Danger] ", "\u001B[31m"),
        DEBUG("[Debug] ", "\u001B[36m");

        @Getter private String prefix;
        @Getter private String color;
    }


    public static void log(Object text){
        if (KrorkAPI.isDebugEnabled()) log(LogType.DEBUG, text);
    }

    public static void log(LogType type, Object text){
        String time = "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)) + "]";
        String log;

        if (System.getProperty("os.name").contains("10")) {
            log = time + type.getColor() + type.getPrefix() + Krork.getGame() + " >> \u001B[0m" + text + "\u001B[0m";
        } else {
            log = time + type.getPrefix() + Krork.getGame() + " >> " + text;
        }
        System.out.println(log);
    }
}
