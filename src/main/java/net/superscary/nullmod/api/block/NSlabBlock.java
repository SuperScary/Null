package net.superscary.nullmod.api.block;

import lombok.Getter;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NSlabBlock extends SlabBlock {

    @Getter
    private final BlockDefinition<?> parent;

    public NSlabBlock(Properties properties, BlockDefinition<?> parent) {
        super(properties);
        this.parent = parent;
    }

}
