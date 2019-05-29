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

package net.athonedevs.krork.saves;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.athonedevs.krork.Krork;
import net.athonedevs.krork.utils.Log;

import java.io.*;
import java.util.Arrays;

public class Save {

    private final File entitySave;
    private final File playerSave;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Save() {
        this("C" + File.pathSeparator + Krork.getGame());
    }
    public Save(String path) {
        entitySave = new File(path, "entities.json");
        playerSave = new File(path, "player.json");

        try {
            if (!playerSave.exists()) playerSave.createNewFile();
            if (!entitySave.exists()) entitySave.createNewFile();
        } catch (IOException e) {
            Log.log(Log.LogType.DANGER, e.getCause());
        }
    }


    public void savePlayer(EntityData player) throws IOException {
        final BufferedWriter bw = new BufferedWriter(new FileWriter(playerSave));

        bw.write(gson.toJson(player));
        bw.flush();
        bw.close();
    }

    public void saveEntities(EntityData... entities) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final BufferedWriter bw = new BufferedWriter(new FileWriter(entitySave));
        final JsonObject ent = new JsonObject();
        final JsonArray array = new JsonArray();

        Arrays.asList(entities).forEach(e -> array.add(gson.toJsonTree(e)));
        ent.add("entities", array);

        bw.write(gson.toJson(ent));
        bw.flush();
        bw.close();
    }

    public EntityData loadData(EntityData data) throws IOException {
        return gson.fromJson(new FileReader(playerSave), data.getClass());
    }
}
