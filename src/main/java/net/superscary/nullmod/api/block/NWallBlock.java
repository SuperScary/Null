package net.superscary.nullmod.api.block;

import lombok.Getter;
import net.minecraft.world.level.block.WallBlock;

public class NWallBlock extends WallBlock {

    @Getter
    private final BlockDefinition<?> parent;

    public NWallBlock(Properties properties, BlockDefinition<?> parent) {
        super(properties);
        this.parent = parent;
    }

}
