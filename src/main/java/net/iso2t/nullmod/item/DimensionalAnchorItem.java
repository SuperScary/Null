package net.iso2t.nullmod.item;

import net.iso2t.nullmod.api.item.base.BaseItem;
import net.iso2t.nullmod.core.Null;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DimensionalAnchorItem extends BaseItem {

    public static final String TAG_DIMENSION = "Dimension";

    public static final ResourceLocation QUARRY_OVERWORLD_ID = Null.getResource("quarry_overworld");
    public static final ResourceLocation QUARRY_NETHER_ID = Null.getResource("quarry_nether");
    public static final ResourceLocation QUARRY_END_ID = Null.getResource("quarry_end");

    public DimensionalAnchorItem(Properties properties) {
        super(properties);
    }

    @Nullable
    public static ResourceLocation getDimensionId(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) return null;
        CompoundTag tag = customData.copyTag();
        if (!tag.contains(TAG_DIMENSION)) return null;
        String id = tag.getString(TAG_DIMENSION);
        if (id.isEmpty()) return null;
        return ResourceLocation.tryParse(id);
    }

    public static void setDimensionId(ItemStack stack, @Nullable ResourceLocation dimensionId) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        CompoundTag tag = customData == null ? new CompoundTag() : customData.copyTag();

        if (dimensionId == null) {
            tag.remove(TAG_DIMENSION);
        } else {
            tag.putString(TAG_DIMENSION, dimensionId.toString());
        }

        if (tag.isEmpty()) {
            stack.remove(DataComponents.CUSTOM_DATA);
        } else {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        }
    }

    public static void setToQuarryOverworld(ItemStack stack) {
        setDimensionId(stack, QUARRY_OVERWORLD_ID);
    }

    public static void setToQuarryNether(ItemStack stack) {
        setDimensionId(stack, QUARRY_NETHER_ID);
    }

    public static void setToQuarryEnd(ItemStack stack) {
        setDimensionId(stack, QUARRY_END_ID);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        ResourceLocation id = getDimensionId(stack);
        if (id == null) {
            tooltipComponents.add(Component.translatable("tooltip." + Null.MODID + ".dimensional_anchor.dimension.not_set"));
            return;
        }

        Component display;
        if (id.equals(QUARRY_OVERWORLD_ID)) {
            display = Component.translatable("tooltip." + Null.MODID + ".dimensional_anchor.dimension.overworld");
        } else if (id.equals(QUARRY_NETHER_ID)) {
            display = Component.translatable("tooltip." + Null.MODID + ".dimensional_anchor.dimension.nether");
        } else if (id.equals(QUARRY_END_ID)) {
            display = Component.translatable("tooltip." + Null.MODID + ".dimensional_anchor.dimension.end");
        } else {
            display = Component.literal(id.toString());
        }

        tooltipComponents.add(Component.translatable("tooltip." + Null.MODID + ".dimensional_anchor.dimension", display));
    }
}
