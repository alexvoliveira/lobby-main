package com.github.alexvoliveira.plugin.lobby.provider.hotbar;

import com.github.alexvoliveira.plugin.core.spigot.common.factory.ItemFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public final class HotbarProvider {

    private List<ItemFactory> items(Player user) {
        return Arrays.asList(
                new ItemFactory(Material.SKULL_ITEM)
                        .durability((short) 3)
                        .owner(user.getName())
                        .name("§aSua Conta.")
                        .lore("§7Clique para abrir.")
                        .slot(2),

                new ItemFactory(Material.COMPASS)
                        .name("§aMode de Jogo")
                        .lore("§7Clique para abrir.")
                        .slot(6)
        );
    }

    public void apply(Player user) {
        items(user).forEach(itemFactory -> {
            user.getInventory().setItem(itemFactory.getSlot(), itemFactory.build());
        });
    }

    public void clear(Player user) {
        items(user).forEach(itemFactory -> {
            user.getInventory().setItem(itemFactory.getSlot(), null);
        });
    }
}
