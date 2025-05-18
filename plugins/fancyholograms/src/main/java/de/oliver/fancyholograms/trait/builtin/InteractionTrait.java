package de.oliver.fancyholograms.trait.builtin;

import de.oliver.fancyholograms.api.data.TextHologramData;
import de.oliver.fancyholograms.api.trait.HologramTrait;
import de.oliver.fancyholograms.api.trait.HologramTraitClass;
import de.oliver.fancyholograms.util.PluginUtils;
import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcAttribute;
import de.oliver.fancynpcs.api.NpcData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApiStatus.Experimental
@HologramTraitClass(traitName = "interaction_trait", defaultTrait = true)
public class InteractionTrait extends HologramTrait {

    private Configuration config;
    private Npc hitbox;

    public InteractionTrait() {
        if (!PluginUtils.isFancyNpcsEnabled()) {
            throw new IllegalStateException("FancyNpcs is not enabled. Please enable it to use InteractionTrait.");
        }

        this.config = new Configuration(new ArrayList<>());
    }

    @Override
    public void onAttach() {
        this.updateHitbox();
    }

    @Override
    public void onSpawn(Player player) {
        this.updateHitbox();
    }

    @Override
    public void onDespawn(Player player) {
        this.updateHitbox();
    }

    @Override
    public void onModify() {
        this.updateHitbox();
    }

    @Override
    public void save() {
        try {
            storage.set(hologram.getData().getName(), config);
        } catch (Exception e) {
            logger.error("Failed to save configuration for InteractionTrait");
            logger.error(e);
        }
    }

    @Override
    public void load() {
        try {
            config = storage.get(hologram.getData().getName(), Configuration.class);
        } catch (Exception e) {
            logger.error("Failed to load configuration for InteractionTrait");
            logger.error(e);
        }
        if (config == null) {
            config = new Configuration(new ArrayList<>());
            save();
        }
    }

    private void updateHitbox() {
        if (FancyNpcsPlugin.get().getNpcManager().getNpc("hologram_hitbox_for_" + hologram.getData().getName()) == null) {
            this.hitbox = FancyNpcsPlugin.get().getNpcAdapter().apply(new NpcData(
                    "hologram_hitbox_for_" + hologram.getData().getName(),
                    UUID.randomUUID(),
                    hologram.getData().getLocation()
            ));
            this.hitbox.setSaveToFile(false);
            this.hitbox.getData().setType(EntityType.INTERACTION);
            this.hitbox.getData().setDisplayName("<empty>");

            // TODO add actions to hitbox

            this.hitbox.create();
            this.hitbox.spawnForAll();
            FancyNpcsPlugin.get().getNpcManager().registerNpc(this.hitbox);
        }

        this.hitbox.getData().setLocation(hologram.getData().getLocation());

        NpcAttribute widthAttr = FancyNpcsPlugin.get().getAttributeManager().getAttributeByName(EntityType.INTERACTION, "width");
        this.hitbox.getData().addAttribute(widthAttr, String.valueOf(calcWidth()));

        NpcAttribute heightAttr = FancyNpcsPlugin.get().getAttributeManager().getAttributeByName(EntityType.INTERACTION, "height");
        this.hitbox.getData().addAttribute(heightAttr, String.valueOf(calcHeight()));

        this.hitbox.updateForAll();
    }

    private double calcHeight() {
        int lines = ((TextHologramData) hologram.getData()).getText().size();
        double heightPerLine = 0.25;
        double scale = ((TextHologramData) hologram.getData()).getScale().y;
        return lines * heightPerLine * scale;
    }

    private double calcWidth() {
        List<String> lines = ((TextHologramData) hologram.getData()).getText();
        double maxCharacters = 0;
        for (String line : lines) {
            if (line.length() > maxCharacters) {
                maxCharacters = line.length();
            }
        }

        double widthPerCharacter = 0.12;
        double scale = ((TextHologramData) hologram.getData()).getScale().x;
        return maxCharacters * widthPerCharacter * scale;
    }

    public enum Action {
        NONE,
        NEXT_PAGE
    }

    public record ActionConfig(
            Action action,
            String value
    ) {

    }

    public record Configuration(
        List<ActionConfig> actions
    ) {


    }
}
