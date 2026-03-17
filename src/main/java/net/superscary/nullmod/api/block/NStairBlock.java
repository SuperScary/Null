package net.superscary.nullmod.api.block;

import lombok.Getter;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class NStairBlock extends StairBlock {

    @Getter
    private final BlockDefinition<?> parent;

    public NStairBlock(BlockState baseState, BlockBehaviour.Properties properties, BlockDefinition<?> parent) {
        super(baseState, properties);
        this.parent = parent;
    }

}
