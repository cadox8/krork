package net.athonedevs.krork.utils;

import net.athonedevs.krork.Krork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Updater {

    public static void checkForUpdate(){
        if (!getEngineVersion().equalsIgnoreCase(Krork.getVersion())) {
            Log.log(Log.LogType.WARNING, "\u001B[35mNew version found, Version: \u001B[31m" + getEngineVersion());
            Log.log(Log.LogType.WARNING, "You can download it from here: \u001B[32mhttps://cadox8.github.io/krork/");
        } else {
            Log.log(Log.LogType.SUCCESS, "No updates found");
        }
    }

    public static String getEngineVersion(){
        try {
            URL u = new URL("https://cadox8.github.io/krork/version.txt");
            return new BufferedReader(new InputStreamReader(u.openStream())).readLine();
        } catch (IOException e){}
        return Krork.getVersion();
    }
}
