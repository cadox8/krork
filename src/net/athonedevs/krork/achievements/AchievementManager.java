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

package net.athonedevs.krork.achievements;

import net.athonedevs.krork.gfx.Sprites;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AchievementManager {

    private ArrayList<Achievement> achievements;

    public AchievementManager() {
        this.achievements = new ArrayList<>();
    }

    public void register(Achievement... achievements) {
        this.achievements.addAll(Arrays.asList(achievements));
    }

    public void tick() {
        achievements.stream().filter(Achievement::isShow).filter(a -> !a.getIcon().isEnd()).forEach(Achievement::tick);
    }

    public void render(Graphics g) {
        achievements.stream().filter(Achievement::isShow).filter(a -> !a.getIcon().isEnd()).forEach(achievement -> achievement.render(g));
    }

    public Achievement getAchievementById(int id) {
        return achievements.stream().filter(a -> a.getId() == id).findAny().orElse(new Achievement(-1, "Error", Sprites.randomImage(16, 16), "This is the default error achievement"));
    }
}
