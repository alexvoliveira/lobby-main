package com.github.alexvoliveira.plugin.lobby.listener.guard;

import com.github.alexvoliveira.plugin.core.spigot.command.BuildCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public final class GuardListener implements Listener {

    @EventHandler
    void onBlockBreak(BlockBreakEvent event) {
        if (BuildCommand.mode.contains(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    void onDropItems(PlayerDropItemEvent event) {
        if (BuildCommand.mode.contains(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    void onPicture(PlayerPickupItemEvent event) {
        if (BuildCommand.mode.contains(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setCancelled(true);
    }
}