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

package net.athonedevs.krork.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.athonedevs.krork.Krork;
import net.athonedevs.krork.utils.Log;

public class Discord {

    public Discord(String token) {
        final DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> Log.log(Log.LogType.SUCCESS, Krork.getGame() + " hooked into Discord (" + user.username + "#" + user.discriminator + ")!")).build();
        DiscordRPC.discordInitialize(token, handlers, true);
    }

    public void updateRichPresence(DiscordRichPresence presence) {
        DiscordRPC.discordUpdatePresence(presence);
    }
}
