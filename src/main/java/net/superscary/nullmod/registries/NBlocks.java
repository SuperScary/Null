package net.superscary.nullmod.registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.superscary.nullmod.api.block.*;
import net.superscary.nullmod.api.block.base.BaseBlock;
import net.superscary.nullmod.api.item.ItemDefinition;
import net.superscary.nullmod.api.item.base.BaseBlockItem;
import net.superscary.nullmod.block.QuarryFrameBlock;
import net.superscary.nullmod.block.SatelliteBlock;
import net.superscary.nullmod.core.Null;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class NBlocks {

    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(Null.MODID);
    private static final List<BlockDefinition<?>> BLOCKS = new ArrayList<>();

    public static final BlockDefinition<SatelliteBlock> QUARRY_SATELLITE = register("Dimensional Quarry Satellite", "quarry_satellite", SatelliteBlock::new);
    public static final BlockDefinition<QuarryFrameBlock> QUARRY_FRAME   = register("Dimensional Quarry Frame", "quarry_frame", QuarryFrameBlock::new);

    public static final BlockDefinition<BaseBlock> MACHINE_FRAME = register("Machine Frame", BaseBlock::new);

    public static final BlockDefinition<DecorationBlock> MARBLE = register("Marble", DecorationBlock::new);
    public static final BlockDefinition<NWallBlock> MARBLE_WALL = register("Marble Wall", () -> new NWallBlock(MARBLE.getBlock().properties(), MARBLE));
    public static final BlockDefinition<NStairBlock> MARBLE_STAIR = register("Marble Stairs", () -> new NStairBlock(MARBLE.getBlock().defaultBlockState(), MARBLE.getBlock().properties(), MARBLE));
    public static final BlockDefinition<NSlabBlock> MARBLE_SLAB = register("Marble Slab", () -> new NSlabBlock(MARBLE.getBlock().properties(), MARBLE));
    public static final BlockDefinition<DecorationBlock> MARBLE_BRICK = register("Marble Brick", DecorationBlock::new);
    public static final BlockDefinition<NWallBlock> MARBLE_BRICK_WALL = register("Marble Brick Wall", () -> new NWallBlock(MARBLE_BRICK.getBlock().properties(), MARBLE_BRICK));
    public static final BlockDefinition<NStairBlock> MARBLE_BRICK_STAIR = register("Marble Brick Stairs", () -> new NStairBlock(MARBLE_BRICK.getBlock().defaultBlockState(), MARBLE_BRICK.getBlock().properties(), MARBLE_BRICK));
    public static final BlockDefinition<NSlabBlock> MARBLE_BRICK_SLAB = register("Marble Brick Slab", () -> new NSlabBlock(MARBLE_BRICK.getBlock().properties(), MARBLE_BRICK));
    public static final BlockDefinition<DecorationBlock> POLISHED_MARBLE = register("Polished Marble", "marble_polished", DecorationBlock::new);
    public static final BlockDefinition<NWallBlock> POLISHED_MARBLE_WALL = register("Polished Marble Wall", () -> new NWallBlock(POLISHED_MARBLE.getBlock().properties(), POLISHED_MARBLE));
    public static final BlockDefinition<NStairBlock> POLISHED_MARBLE_STAIR = register("Polished Marble Stairs", () -> new NStairBlock(POLISHED_MARBLE.getBlock().defaultBlockState(), POLISHED_MARBLE.getBlock().properties(), POLISHED_MARBLE));
    public static final BlockDefinition<NSlabBlock> POLISHED_MARBLE_SLAB = register("Polished Marble Slab", () -> new NSlabBlock(POLISHED_MARBLE.getBlock().properties(), POLISHED_MARBLE));

    public static List<BlockDefinition<?>> getBlocks () {
        return Collections.unmodifiableList(BLOCKS);
    }

    public static <T extends Block> BlockDefinition<T> register(final String name, final Supplier<T> supplier) {
        String resourceFriendly = name.toLowerCase().replace(' ', '_');
        return register(name, Null.getResource(resourceFriendly), supplier, null, true);
    }

    public static <T extends Block> BlockDefinition<T> register(final String name, String resourceName, final Supplier<T> supplier) {
        return register(name, Null.getResource(resourceName), supplier, null, true);
    }

    public static <T extends Block> BlockDefinition<T> register(final String name, ResourceLocation id, final Supplier<T> supplier, @Nullable BiFunction<Block, Item.Properties, BlockItem> itemFactory, boolean addToTab) {
        return register(name, id, supplier, itemFactory, addToTab, NTab.MAIN);
    }

    public static <T extends Block> BlockDefinition<T> register(final String name, ResourceLocation id, final Supplier<T> supplier, @Nullable BiFunction<Block, Item.Properties, BlockItem> itemFactory, boolean addToTab, @Nullable ResourceKey<CreativeModeTab> group) {
        var deferredBlock = REGISTRY.register(id.getPath(), supplier);
        var deferredItem = NItems.REGISTRY.register(id.getPath(), () -> {
            var block = deferredBlock.get();
            var itemProperties = new Item.Properties();
            if (itemFactory != null) {
                var item = itemFactory.apply(block, itemProperties);
                if (item == null) {
                    throw new IllegalArgumentException("BlockItem factory for " + id + " returned null.");
                }
                return item;
            } else if (block instanceof BaseBlock) {
                return new BaseBlockItem(block, itemProperties);
            } else {
                return new BlockItem(block, itemProperties);
            }
        });
        var itemDef = new ItemDefinition<>(name, deferredItem);
        if (addToTab) {
            if (Objects.equals(group, NTab.MAIN)) {
                NTab.add(itemDef);
            } else if (group != null) {
                NTab.addExternal(group, itemDef);
            }
        }
        BlockDefinition<T> definition = new BlockDefinition<>(name, deferredBlock, itemDef);
        BLOCKS.add(definition);
        return definition;
    }

}
