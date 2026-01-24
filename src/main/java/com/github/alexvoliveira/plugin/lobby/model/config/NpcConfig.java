package com.github.alexvoliveira.plugin.lobby.model.config;

import com.bnstra.npclib.api.skin.Skin;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

@Data
@Builder
public final class NpcConfig {

    private String name;
    private List<String> displayLines;
    private Skin skin;
    private Material handItem;
    private Location location;
}
