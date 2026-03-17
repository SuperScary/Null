package net.superscary.nullmod.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.superscary.nullmod.api.IDataProvider;
import net.superscary.nullmod.api.block.NSlabBlock;
import net.superscary.nullmod.api.block.NStairBlock;
import net.superscary.nullmod.api.block.NWallBlock;
import net.superscary.nullmod.core.Null;
import net.superscary.nullmod.registries.NBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider implements IDataProvider {

    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Null.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        for (var block : NBlocks.getBlocks()) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.getBlock());
            if (!(block.getBlock() instanceof NWallBlock) && !(block.getBlock() instanceof NSlabBlock) && !(block.getBlock() instanceof NStairBlock))
                this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block.getBlock());

            if (block.getBlock() instanceof NWallBlock) {
                this.tag(BlockTags.WALLS).add(block.getBlock());
                this.tag(BlockTags.NEEDS_STONE_TOOL).add(block.getBlock());
            }

            if (block.getBlock() instanceof NSlabBlock) {
                this.tag(BlockTags.SLABS).add(block.getBlock());
                this.tag(BlockTags.NEEDS_STONE_TOOL).add(block.getBlock());
            }

            if (block.getBlock() instanceof NStairBlock) {
                this.tag(BlockTags.STAIRS).add(block.getBlock());
                this.tag(BlockTags.NEEDS_STONE_TOOL).add(block.getBlock());
            }
        }
    }
}
