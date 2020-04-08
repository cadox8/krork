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

import com.google.gson.GsonBuilder;
import net.athonedevs.krork.Krork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class Updater {

    public static void checkForUpdate() {
        if (getEngineVersion().latest > Krork.getVersionNumber()) {
            Log.warning(Colors.GREEN.getColor() + "New version found, Version: " + Colors.RED.getColor() + Arrays.stream(getEngineVersion().older).filter(v -> v.id == getEngineVersion().latest).findAny().get().version, "Krork");
            Log.warning("You can download it from here: https://cadox8.github.io/krork/", "Krork");
            Log.warning("Or just update your pom.xml file", "Krork");
        } else {
            Log.success("No updates found", "Krork");
        }
    }

    public static Versions getEngineVersion() {
        try {
            URLConnection connection = new URL("https://cadox8.github.io/krork/versions.json").openConnection();
            final String redirect = connection.getHeaderField("Location");
            if (redirect != null) connection = new URL(redirect).openConnection();
            final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final StringBuilder sb = new StringBuilder();
            String lines;
            while ((lines = br.readLine()) != null) sb.append(lines);

            return new GsonBuilder().setPrettyPrinting().create().fromJson(sb.toString(), Versions.class);
        } catch (IOException e) {
            final Versions versions = new Versions();
            versions.latest = Krork.getVersionNumber();
            versions.older = new Versions.VersionData[0];
            return versions;
        }
    }

    public static class Versions {

        public int latest;
        public VersionData[] older;

        public class VersionData {
            public int id;
            public String version;
        }
    }
}
