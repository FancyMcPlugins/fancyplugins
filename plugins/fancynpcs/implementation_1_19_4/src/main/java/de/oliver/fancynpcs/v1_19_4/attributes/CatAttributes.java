package de.oliver.fancynpcs.v1_19_4.attributes;

import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcAttribute;
import de.oliver.fancynpcs.v1_19_4.ReflectionHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.item.DyeColor;
import org.bukkit.entity.EntityType;
import org.checkerframework.checker.units.qual.N;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatAttributes {

    public static List<NpcAttribute> getAllAttributes() {
        List<NpcAttribute> attributes = new ArrayList<>();

        attributes.add(new NpcAttribute(
                "variant",
                Arrays.stream(org.bukkit.entity.Cat.Type.values())
                        .map(Enum::name)
                        .toList(),
                List.of(EntityType.CAT),
                CatAttributes::setVariant
        ));

        attributes.add(new NpcAttribute(
                "pose",
                List.of("standing", "sleeping", "sitting"),
                List.of(EntityType.CAT),
                CatAttributes::setPose
        ));

        attributes.add(new NpcAttribute(
                "color",
                List.of("RED", "BLUE", "YELLOW", "GREEN", "PURPLE", "ORANGE", "LIME", "MAGENTA", "BROWN", "WHITE", "GRAY", "LIGHT_GRAY", "LIGHT_BLUE", "BLACK", "CYAN", "PINK", "NONE"),
                List.of(EntityType.CAT),
                CatAttributes::setColor
        ));

        return attributes;
    }

    private static void setVariant(Npc npc, String value) {
        final Cat cat = ReflectionHelper.getEntity(npc);
        BuiltInRegistries.CAT_VARIANT.getOptional(ResourceLocation.of(value.toLowerCase(), ':'))
                .ifPresent(cat::setVariant);
    }

    private static void setPose(Npc npc, String value) {
        final Cat cat = ReflectionHelper.getEntity(npc);
        switch (value.toLowerCase()) {
            case "standing" -> {
                cat.setInSittingPose(false, false);
                cat.setLying(false);
            }
            case "sleeping" -> {
                cat.setInSittingPose(false, false);
                cat.setLying(true);
            }
            case "sitting" -> {
                cat.setLying(false);
                cat.setOrderedToSit(true);
                cat.setInSittingPose(true, false);
            }
        }
    }

    private static void setColor(Npc npc, String value){
        final Cat cat = ReflectionHelper.getEntity(npc);

        if (value.equalsIgnoreCase("none") || value.isEmpty()){
            cat.setTame(false);
            return;
        }

        try {
            DyeColor color = DyeColor.valueOf(value.toUpperCase());
            if (!cat.isTame()){
                cat.setTame(true);
            }
            cat.setCollarColor(color);
        } catch (IllegalArgumentException e){
            System.out.print("Invalid Color: " + value);
        }
    }

}
