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

import net.athonedevs.krork.Krork;
import net.athonedevs.krork.api.KrorkAPI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Log {

    public enum LogType {
        SUCCESS("[Success] ", "\u001B[32m"),
        NORMAL("", ""),
        WARNING("[Warning] ", "\u001B[33m"),
        DANGER("[Danger] ", "\u001B[31m"),
        DEBUG("[Debug] ", "\u001B[36m");

        private String prefix;
        private String color;

        LogType(String prefix, String color) {
            this.prefix = prefix;
            this.color = color;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public String getColor() {
            return this.color;
        }
    }

    /**
     * Logs the info as Debug
     * @see KrorkAPI#isDebugEnabled()
     *
     * @param info The object to be logged
     */
    public static void log(Object info){
        if (KrorkAPI.isDebugEnabled()) log(LogType.DEBUG, info);
    }

    /**
     * Logs the info as Danger
     *
     * @param info The object to be logged
     */
    public static void danger(Object info) {
        log(LogType.DANGER, info);
    }

    /**
     * Logs the info as Warning
     *
     * @param info The object to be logged
     */
    public static void warning(Object info) {
        log(LogType.WARNING, info);
    }

    /**
     * Logs the info as Normal
     *
     * @param info The object to be logged
     */
    public static void normal(Object info) {
        log(LogType.NORMAL, info);
    }

    /**
     * Logs the info as Success
     *
     * @param info The object to be logged
     */
    public static void success(Object info) {
        log(LogType.SUCCESS, info);
    }


    /**
     * Logs the info as the type you select
     * @see KrorkAPI#isDebugEnabled()
     * @see LogType
     *
     * @param type The log type
     * @param info The object to be logged
     */
    public static void log(LogType type, Object info){
        final String time = "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)) + "]";
        String log;

        if (type == LogType.DEBUG && !KrorkAPI.isDebugEnabled()) return;

        if (System.getProperty("os.name").contains("10")) {
            log = time + type.getColor() + type.getPrefix() + Krork.getGame() + " >> \u001B[0m" + info + "\u001B[0m";
        } else {
            log = time + type.getPrefix() + Krork.getGame() + " >> " + info;
        }
        System.out.println(log);
    }
}
