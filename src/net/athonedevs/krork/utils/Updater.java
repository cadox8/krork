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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Updater {

    public static void checkForUpdate(){
        if (!getEngineVersion().equalsIgnoreCase(Krork.getVersion())) {
            Log.log(Log.LogType.WARNING, Colors.GREEN.getColor() + "New version found, Version: " + Colors.RED.getColor() + getEngineVersion(), "Krork");
            Log.log(Log.LogType.WARNING, "You can download it from here: https://cadox8.github.io/krork/", "Krork");
        } else {
            Log.log(Log.LogType.SUCCESS, "No updates found", "Krork");
        }
    }

    public static String getEngineVersion(){
        try {
            URLConnection connection = new URL("https://cadox8.github.io/krork/version.txt").openConnection();
            final String redirect = connection.getHeaderField("Location");
            if (redirect != null) connection = new URL(redirect).openConnection();
            final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return br.readLine();
        } catch (IOException e) {
            return Krork.getVersion();
        }
    }
}
