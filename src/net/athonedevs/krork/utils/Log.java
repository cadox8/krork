package net.athonedevs.krork.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.athonedevs.krork.api.KrorkAPI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Log {

    @AllArgsConstructor
    public enum LogType {
        SUCCESS("[Success]", "\u001B[32m"),
        NORMAL("", ""),
        WARNING("[Warning]", "\u001B[33m"),
        DANGER("[Danger]", "\u001B[31m"),
        DEBUG("[Debug]", "\u001B[36m");

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
            log = time + type.getColor() + type.getPrefix() + " \u001B[0m" + text + "\u001B[0m";
        } else {
            log = time + type.getPrefix() + " " + text;
        }
        System.out.println(log);
    }
}
