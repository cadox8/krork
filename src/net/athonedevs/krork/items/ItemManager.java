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

package net.athonedevs.krork.items;

import net.athonedevs.krork.ex.ItemRegisteredException;
import net.athonedevs.krork.utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemManager {

    private static List<Item> items;

    public ItemManager() {
        items = new ArrayList<>();
    }

    /**
     * Register items
     *
     * @param items The items to be added
     */
    public void addItems(Item... items) {
        Arrays.asList(items).forEach(this::addItem);
    }

    /**
     * Register items
     *
     * @param item The item to be added
     */
    public void addItem(Item item) {
        try {
            if (items.stream().anyMatch(i -> i.getId() == item.getId())) throw new ItemRegisteredException(item.getId());
            items.add(item);
        } catch (ItemRegisteredException e) {
            Log.log(Log.LogType.DANGER, e.getMessage());
        }
    }

    /**
     * Gets the Item by its ID
     *
     * @param id The ID of the item
     * @return The Item or null
     */
    public Item getItemByID(int id) {
        return items.stream().filter(i -> i.getId() == id).findAny().orElse(null);
    }
}
