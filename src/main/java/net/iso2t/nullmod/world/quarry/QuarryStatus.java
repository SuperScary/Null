package net.iso2t.nullmod.world.quarry;

import net.iso2t.nullmod.core.Null;
import net.minecraft.network.chat.Component;

import java.util.Locale;

public enum QuarryStatus {
    MINING,
    QUARRY_DIMENSION_MISSING,
    NO_BLOCKS_SKIPPING,
    NO_ENERGY,
    FLUID_TANK_FULL_SKIPPING_SOURCE_FLUIDS,
    SKIPPING_FLOWING_FLUID,
    EXPORT_FULL;

    public Component asComponent() {
        return Component.translatable("status." + Null.MODID + ".quarry." + name().toLowerCase(Locale.ROOT));
    }

    public static QuarryStatus fromSaved(String value) {
        if (value == null || value.isBlank()) return null;

        try {
            return QuarryStatus.valueOf(value);
        } catch (IllegalArgumentException ignored) {
        }

        return switch (value) {
            case "Mining" -> MINING;
            case "Quarry dimension missing" -> QUARRY_DIMENSION_MISSING;
            case "No blocks (skipping)" -> NO_BLOCKS_SKIPPING;
            case "No energy" -> NO_ENERGY;
            case "Fluid tank full (skipping source fluids)" -> FLUID_TANK_FULL_SKIPPING_SOURCE_FLUIDS;
            case "Skipping flowing fluid" -> SKIPPING_FLOWING_FLUID;
            case "Export full" -> EXPORT_FULL;
            default -> null;
        };
    }
}
