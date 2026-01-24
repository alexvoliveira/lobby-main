package com.github.alexvoliveira.plugin.lobby.service;

import com.bnstra.npclib.NPCLib;
import com.bnstra.npclib.api.NPC;
import com.bnstra.npclib.api.state.NPCSlot;
import com.github.alexvoliveira.plugin.core.spigot.common.enums.SkinsNpcEnums;
import com.github.alexvoliveira.plugin.lobby.LobbyPlugin;
import com.github.alexvoliveira.plugin.lobby.model.config.NpcConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@RequiredArgsConstructor
public final class NpcService {

    private final LobbyPlugin plugin;
    private final List<NPC> npcList = new ArrayList<>();
    private NPCLib npcLib;

    public void create() {
        this.npcLib = new NPCLib(plugin);

        loadNPCsFromConfig().forEach(this::createNPC);
    }

    private void createNPC(NpcConfig npcConfig) {
        NPC npc = npcLib.createNPC(npcConfig.getDisplayLines());
        npc.setSkin(npcConfig.getSkin());
        npc.setItem(NPCSlot.MAINHAND, new ItemStack(npcConfig.getHandItem()));
        npc.setLocation(npcConfig.getLocation());
        npc.create();
        npcList.add(npc);
    }

    private List<NpcConfig> loadNPCsFromConfig() {
        return Arrays.asList(
                NpcConfig.builder()
                        .name("skywars")
                        .displayLines(Arrays.asList("§b§lSKYWARS", "§7Clique para entrar."))
                        .skin(SkinsNpcEnums.SKYWARS.skin())
                        .handItem(Material.EYE_OF_ENDER)
                        .location(createLocation(-137.500, 65, 253.500))
                        .build(),

                NpcConfig.builder()
                        .name("bedwars")
                        .displayLines(Arrays.asList("§b§lBEDWARS", "§7Clique para entrar."))
                        .skin(SkinsNpcEnums.BEDWARS.skin())
                        .handItem(Material.BED)
                        .location(createLocation(-135.500, 65, 253.500))
                        .build(),

                NpcConfig.builder()
                        .name("events")
                        .displayLines(Arrays.asList("§b§lEVENTOS", "§7Clique para entrar."))
                        .skin(SkinsNpcEnums.BEDWARS.skin())
                        .handItem(Material.NETHER_STAR)
                        .location(createLocation(-139.500, 65, 253.500))
                        .build()
        );
    }

    private Location createLocation(double x, double y, double z) {
        World world = Bukkit.getWorld("world");
        if (world == null) {
            throw new IllegalStateException("World 'world' not found");
        }
        return new Location(world, x, y, z, 0.0f, 0.0f);
    }

    public void destroy() {
        npcList.forEach(NPC::destroy);
        npcList.clear();
    }

    public List<NPC> getNPCs() {
        return Collections.unmodifiableList(npcList);
    }
}