package com.github.alexvoliveira.plugin.lobby;

import com.github.alexvoliveira.plugin.lobby.listener.npc.NpcListener;
import com.github.alexvoliveira.plugin.lobby.listener.player.PlayerListener;
import com.github.alexvoliveira.plugin.lobby.provider.hotbar.HotbarProvider;
import com.github.alexvoliveira.plugin.lobby.service.NpcService;
import com.google.common.base.Stopwatch;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class LobbyPlugin extends JavaPlugin {

    private NpcService npcService;
    private HotbarProvider hotbarProvider;

    @Override
    public void onEnable() {
        final Stopwatch stopwatch = Stopwatch.createStarted();

        loadNpcs();
        loadListeners();
        loadItem();

        stopwatch.stop();
        getServer().getConsoleSender().sendMessage(
                "§a§lLOBBY SPIGOT ➜ §fPlugin §ainiciado §7com §asucesso! §7Em: §a" + stopwatch);
    }

    @Override
    public void onDisable() {
        final Stopwatch stopwatch = Stopwatch.createStarted();

        if (npcService != null) {
            npcService.destroy();
        }

        stopwatch.stop();
        getServer().getConsoleSender().sendMessage(
                "§4§lLOBBY SPIGOT ➜ §7Plugin §cdesligado §7com §asucesso! §7Em: §a" + stopwatch);
    }

    private void loadNpcs() {
        npcService = new NpcService(this);

        try {
            npcService.create();
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(
                    "§4§lLOBBY SPIGOT ➜ §7Houve um §cproblema §7ao criar o §a§lNPC: §c" + e.getMessage());
            e.printStackTrace();
        }

    }

    private void loadListeners() {
        PluginManager pm = getServer().getPluginManager();

        try {
            pm.registerEvents(new NpcListener(this), this);
            pm.registerEvents(new PlayerListener(this), this);
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(
                    "§4§lLOBBY SPIGOT ➜ §7Houve um §cproblema §7ao §aregistrar §7a listener: §c" + e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private void loadItem() {
        try {
            hotbarProvider = new HotbarProvider();
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(
                    "§4§lLOBBY SPIGOT ➜ §7Houve um §cproblema §7ao §ainiciar a §ahotbar: §c" + e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}