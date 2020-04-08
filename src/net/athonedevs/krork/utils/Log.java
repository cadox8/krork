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

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Log {

    public enum LogType {
        SYSTEM("[System]"),
        SUCCESS("[Success]"),
        NORMAL(""),
        WARNING("[Warning]"),
        DANGER("[Danger]"),
        DEBUG("[Debug]");

        private final String prefix;

        LogType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }
    }

    private static final ColoredPrinter debug = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.MAGENTA).build();
    private static final ColoredPrinter danger = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.RED).build();
    private static final ColoredPrinter warning = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.YELLOW).build();
    private static final ColoredPrinter normal = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.WHITE).build();
    private static final ColoredPrinter success = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.GREEN).build();
    private static final ColoredPrinter system = new ColoredPrinter.Builder(1, true).foreground(Ansi.FColor.CYAN).build();

    /**
     * Logs the info as Debug System
     *
     * @param info The object to be logged
     */
    public static void system(Object info){
        log(system, LogType.SYSTEM, info, "Krork");
    }
    /**
     * Logs the info as Debug System
     *
     * @param info The object to be logged
     * @param prefix
     */
    public static void system(Object info, String prefix){
        log(system, LogType.SYSTEM, info, prefix);
    }

    /**
     * Logs the info as Debug
     *
     * @param info The object to be logged
     */
    public static void log(Object info){
        log(debug, LogType.DEBUG, info, "Krork");
    }
    public static void log(Object info, String prefix){
        log(debug, LogType.DEBUG, info, prefix);
    }

    /**
     * Logs the info as Danger
     *
     * @param info The object to be logged
     */
    public static void danger(Object info) {
        log(danger, LogType.DANGER, info, "Krork");
    }
    public static void danger(Object info, String prefix) {
        log(danger, LogType.DANGER, info, prefix);
    }

    /**
     * Logs the info as Warning
     *
     * @param info The object to be logged
     */
    public static void warning(Object info) {
        log(warning, LogType.WARNING, info, "Krork");
    }
    public static void warning(Object info, String prefix) {
        log(warning, LogType.WARNING, info, prefix);
    }

    /**
     * Logs the info as Normal
     *
     * @param info The object to be logged
     */
    public static void normal(Object info) {
        log(normal, LogType.NORMAL, info, "Krork");
    }
    public static void normal(Object info, String prefix) {
        log(normal, LogType.NORMAL, info, prefix);
    }

    /**
     * Logs the info as Success
     *
     * @param info The object to be logged
     */
    public static void success(Object info) {
        log(success, LogType.SUCCESS, info, "Krork");
    }
    public static void success(Object info, String prefix) {
        log(success, LogType.SUCCESS, info, prefix);
    }

    /**
     * Logs the info as the type you select
     * @see LogType
     *
     * @param type The log type
     * @param text The object to be logged
     */
    private static void log(ColoredPrinter printer, LogType type, Object text, String prefix){
        final String time = "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)) + "]";
        final String log = time + "[" + prefix + "] " + type.getPrefix() + text;
        printer.setTimestamping(false);
        printer.println(log);
    }
}
