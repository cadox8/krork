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
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import net.athonedevs.krork.Krork;
import net.athonedevs.krork.stats.Stats;
import net.athonedevs.krork.utils.Log;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;

public class Save {

    private final File entitySave;
    private final File playerSave;
    private final File statsFile;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Default Save constructor. All files are saved in C:\\(GameName)
     * @see Krork#getGame()
     *
     * @param worldName The world to create the folder to save the entities per world
     */
    public Save(String worldName) {
        this("C" + File.pathSeparator + Krork.getGame() + File.pathSeparator, worldName);
    }

    /**
     * Save constructor with a path to save the files
     *
     * @param path The path to save the files
     * @param worldName The world to create the folder to save the entities per world
     */
    public Save(String path, String worldName) {
        entitySave = new File(path + File.pathSeparator + worldName, "entities.json");
        playerSave = new File(path, "player.json");
        statsFile = new File(path, "stats.json");

        try {
            if (!playerSave.exists()) playerSave.createNewFile();
            if (!entitySave.exists()) entitySave.createNewFile();
            if (!statsFile.exists()) statsFile.createNewFile();
        } catch (IOException e) {
            Log.danger(e.getCause());
            System.exit(13);
        }
    }

    /**
     * Method to save others files than aren't entities in JSON format
     *
     * @param file The file to be saved in
     * @param save The object to save
     * @throws IOException FileWriter exception
     */
    public void saveMoreFiles(File file, Object save) throws IOException {
        if (!file.exists()) file.createNewFile();
        final BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write(gson.toJson(save));
        bw.flush();
        bw.close();
    }

    public void saveStats(Stats stats) throws IOException {
        stats.setTimePlayer(System.currentTimeMillis() - stats.getFirstRun());
        final BufferedWriter bw = new BufferedWriter(new FileWriter(statsFile));

        bw.write(gson.toJson(stats));
        bw.flush();
        bw.close();
    }

    /**
     * A method to save a player (if exits)
     *
     * @param player The Player EntityData
     * @throws IOException FileWriter exception
     */
    public void savePlayer(Class<? extends EntityData> player) throws IOException {
        saveMoreFiles(playerSave, player);
    }

    /**
     * A method to save all Entities in one file
     *
     * @param entities The entities to be saved
     * @throws IOException FileWriter exception
     */
    @SafeVarargs
    public final void saveEntities(Class<? extends EntityData>... entities) throws IOException {
        final BufferedWriter bw = new BufferedWriter(new FileWriter(entitySave));
        final JsonObject ent = new JsonObject();
        final JsonArray array = new JsonArray();

        Arrays.asList(entities).forEach(e -> array.add(gson.toJsonTree(e)));
        ent.add("entities", array);

        bw.write(gson.toJson(ent));
        bw.flush();
        bw.close();
    }

    public Stats loadStats(Class<? extends Stats> stats) throws IOException {
        return gson.fromJson(new JsonReader(new FileReader(statsFile)), stats);
    }

    /**
     * Method to load all data from a file (Used while loading one entity per file)
     *
     * @param file The file from where we load the entities or data
     * @param data The class to load the data (Your extension of EntityData)
     * @return Your EntityData (or the default EntityData)
     * @throws IOException FileReader exception
     */
    public EntityData loadSingleData(File file, Class<? extends EntityData> data) throws IOException {
        return gson.fromJson(new JsonReader(new FileReader(file)), data);
    }

    /**
     * Method to load all data from a file (Used while loading multiple entity per file)
     *
     * @param file The file from where we load the entities or data
     * @param data The class to load the data (Your extension of EntityData)
     * @return Your EntityData (or the default EntityData)
     * @throws IOException FileReader exception
     */
    public EntityData[] loadMultipleData(File file, Class<? extends EntityData> data) throws IOException {
        return gson.fromJson(new JsonReader(new FileReader(file)), TypeToken.getParameterized(Collection.class, data).getType());
    }

    public File getEntitySave() {
        return this.entitySave;
    }
    public File getPlayerSave() {
        return this.playerSave;
    }
    public File getStatsFile() {
        return statsFile;
    }
}
