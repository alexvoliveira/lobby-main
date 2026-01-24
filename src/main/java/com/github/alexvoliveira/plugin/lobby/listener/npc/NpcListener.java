package com.github.alexvoliveira.plugin.lobby.listener.npc;

import com.bnstra.npclib.api.NPC;
import com.bnstra.npclib.api.events.NPCInteractEvent;
import com.github.alexvoliveira.plugin.lobby.LobbyPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public final class NpcListener implements Listener {

    private final LobbyPlugin plugin;

    @EventHandler
    void onNPCShow(PlayerJoinEvent event) {
        for(NPC npc : plugin.getNpcService().getNPCs()) {
            npc.show(event.getPlayer());
        }
    }

    @EventHandler
    void onNPCInteract(NPCInteractEvent event) {
        NPC click = event.getNPC();

        if (click.getText().get(0).contains("SKYWARS")) {
            event.getWhoClicked().sendMessage("SkyWars");
            return;
        }

        if (click.getText().get(0).contains("BEDWARS")) {
            event.getWhoClicked().sendMessage("§aError: §7Servidor em manutenção.");
        }
    }
}
