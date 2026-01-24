package com.github.alexvoliveira.plugin.lobby.listener.player;

import com.github.alexvoliveira.plugin.core.spigot.common.util.PlaceholderUtil;
import com.github.alexvoliveira.plugin.lobby.LobbyPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public final class PlayerListener implements Listener {

    private final LobbyPlugin plugin;

    private final String rolePath = "lobby.event.permission.traffic";

    @EventHandler
    void onUserJoin(PlayerJoinEvent event) {
        Player user = event.getPlayer();

        plugin.getHotbarProvider().apply(user);

        if (!user.hasPermission(rolePath)) {
            event.setJoinMessage(null);
        }

        event.setJoinMessage(PlaceholderUtil.getRole(user) + "§7Entrou no servidor!");
    }

    @EventHandler
    void onUserQuit(PlayerQuitEvent event) {
        Player user = event.getPlayer();

        plugin.getHotbarProvider().clear(user);

        if (!user.hasPermission(rolePath)) {
            event.setQuitMessage(null);
        }

        event.setQuitMessage(PlaceholderUtil.getRole(user) + "§7Saiu no servidor!");
    }
}
