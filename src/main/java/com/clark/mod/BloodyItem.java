package com.clark.mod;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

class BloodyItem implements Tier {

    static class Holder {
        static BloodyItem getInstance() {
            return new BloodyItem();
        }
    }

    private BloodyItem() {
    }

    // 耐久？
    @Override
    public int getUses() {
        return 99999999;
    }

    @Override
    public float getSpeed() {
        return 15.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 9.0F;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 25;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ItemTags.STONE_TOOL_MATERIALS);
    }

    @org.jetbrains.annotations.Nullable
    public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getTag() {
        return BlockTags.NEEDS_STONE_TOOL;
    }
}
