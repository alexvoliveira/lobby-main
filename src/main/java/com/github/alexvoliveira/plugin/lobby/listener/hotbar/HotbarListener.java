package com.github.alexvoliveira.plugin.lobby.listener.hotbar;

import com.github.alexvoliveira.plugin.core.spigot.view.AccountView;
import com.github.alexvoliveira.plugin.core.spigot.view.ServerView;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HotbarListener implements Listener {

    @EventHandler
    void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || item.getType() == Material.AIR) {
            return;
        }

        if (item.getType() == Material.SKULL_ITEM) {
            new AccountView().openInventory(player);
            return;
        }

        if (item.getType() == Material.COMPASS) {
            new ServerView().openInventory(player);
            return;
        }
    }
}
