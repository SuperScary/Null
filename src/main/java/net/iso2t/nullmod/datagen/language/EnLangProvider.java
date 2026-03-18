package net.iso2t.nullmod.datagen.language;

import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.iso2t.nullmod.api.data.IDataProvider;
import net.iso2t.nullmod.core.Null;
import net.iso2t.nullmod.registries.NBlocks;
import net.iso2t.nullmod.registries.NItems;

public class EnLangProvider extends LanguageProvider implements IDataProvider {

    public EnLangProvider(DataGenerator generator) {
        super(generator.getPackOutput(), Null.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addManualStrings();

        // ITEMS
        for (var item : NItems.getItems()) {
            add(item.asItem(), item.englishName());
        }

        // BLOCKS
        for (var block : NBlocks.getBlocks()) {
            add(block.getBlock(), block.getEnglishName());
        }
    }

    protected void addManualStrings() {
        add("itemGroup." + Null.MODID, Null.NAME);
        add("menu." + Null.MODID + ".quarry", "Dimensional Quarry");

        add("tooltip." + Null.MODID + ".dimensional_anchor.dimension", "§8§oDimension: %s");
        add("tooltip." + Null.MODID + ".dimensional_anchor.dimension.not_set", "§8§oDimension: Not set");
        add("tooltip." + Null.MODID + ".dimensional_anchor.dimension.overworld", "§8§oOverworld");
        add("tooltip." + Null.MODID + ".dimensional_anchor.dimension.nether", "§8§oNether");
        add("tooltip." + Null.MODID + ".dimensional_anchor.dimension.end", "§8§oEnd");

        add("gui." + Null.MODID + ".quarry.term.header", "STATUS");
        add("gui." + Null.MODID + ".quarry.term.status", "Status: %s");
        add("gui." + Null.MODID + ".quarry.term.head", "Head: X=%s Y=%s Z=%s");
        add("gui." + Null.MODID + ".quarry.term.biome", "Biome: %s");
        add("gui." + Null.MODID + ".quarry.term.power", "Power: %s");
        add("gui." + Null.MODID + ".quarry.term.blocks_mined", "Blocks Mined: %s");

        add("status." + Null.MODID + ".quarry.mining", "Mining");
        add("status." + Null.MODID + ".quarry.quarry_dimension_missing", "Quarry dimension missing");
        add("status." + Null.MODID + ".quarry.no_blocks_skipping", "No blocks (skipping)");
        add("status." + Null.MODID + ".quarry.no_energy", "No energy");
        add("status." + Null.MODID + ".quarry.fluid_tank_full_skipping_source_fluids", "Fluid tank full (skipping source fluids)");
        add("status." + Null.MODID + ".quarry.skipping_flowing_fluid", "Skipping flowing fluid");
        add("status." + Null.MODID + ".quarry.export_full", "Export full");

        add("gui." + Null.MODID + ".quarry.power.active", "Active");
        add("gui." + Null.MODID + ".quarry.power.idle", "Idle");
        add("gui." + Null.MODID + ".quarry.power.unformed", "Unformed");
        add("gui." + Null.MODID + ".quarry.power.redstone_off", "Disabled (Redstone)");
    }
}
