package net.superscary.nullmod.api.block;

import net.minecraft.world.level.block.Blocks;
import net.superscary.nullmod.api.block.base.BaseBlock;

public class DecorationBlock extends BaseBlock {

    public DecorationBlock(Properties properties) {
        super(properties);
    }

    public DecorationBlock() {
        this(Properties.ofFullCopy(Blocks.STONE));
    }

}
