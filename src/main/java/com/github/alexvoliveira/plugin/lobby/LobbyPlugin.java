package com.github.alexvoliveira.plugin.lobby;

import com.github.alexvoliveira.plugin.lobby.listener.guard.GuardListener;
import com.github.alexvoliveira.plugin.lobby.listener.hotbar.HotbarListener;
import com.github.alexvoliveira.plugin.lobby.listener.npc.NpcListener;
import com.github.alexvoliveira.plugin.lobby.listener.player.PlayerListener;
import com.github.alexvoliveira.plugin.lobby.provider.hotbar.HotbarProvider;
import com.github.alexvoliveira.plugin.lobby.service.NpcService;
import com.google.common.base.Stopwatch;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@SuppressWarnings("UnstableApiUsage")
public final class LobbyPlugin extends JavaPlugin {

    private NpcService npcService;
    private HotbarProvider hotbarProvider;

    @Override
    public void onEnable() {
        final Stopwatch stopwatch = Stopwatch.createStarted();

        if (!validateDependencies()) {
            getLogger().severe("§c➜ Desabilitando plugin devido o core ausentes.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

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

    private boolean validateDependencies() {
        boolean core = getServer().getPluginManager().getPlugin("core-main") != null;

        if (!core) {
            getLogger().severe("Core não encontrado!");
            return false;
        }

        return true;
    }

    private void loadNpcs() {
        npcService = new NpcService(this);

        try {
            npcService.create();
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(
                    "§4§lLOBBY SPIGOT ➜ §7Houve um §cproblema §7ao criar o §a§lNPC: §c" + e.getMessage());
            getLogger().severe("Erro ao criar NPC: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void loadListeners() {
        PluginManager pm = getServer().getPluginManager();

        try {
            pm.registerEvents(new NpcListener(this), this);
            pm.registerEvents(new PlayerListener(this), this);
            pm.registerEvents(new HotbarListener(), this);
            pm.registerEvents(new GuardListener(), this);
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(
                    "§4§lLOBBY SPIGOT ➜ §7Houve um §cproblema §7ao §aregistrar §7a listener: §c" + e.getMessage());
            getLogger().severe("Erro ao registrar listeners: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void loadItem() {
        try {
            hotbarProvider = new HotbarProvider();
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(
                    "§4§lLOBBY SPIGOT ➜ §7Houve um §cproblema §7ao §ainiciar a §ahotbar: §c" + e.getMessage());
            getLogger().severe("Erro ao iniciar hotbar: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}