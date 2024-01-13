package com.clark.mod;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

enum BloodyArmorMaterial implements ArmorMaterial, StringRepresentable {
    BLOODYITEM("leather", 99999999, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 200);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 200);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 200);
        p_266655_.put(ArmorItem.Type.HELMET, 200);
    }), 15, SoundEvents.ARMOR_EQUIP_GOLD, 200.0F, 0.1F, () -> {
        return Ingredient.of(Items.GOLD_INGOT);
    });

    private final String name;
    private final int durability;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    BloodyArmorMaterial(String name,
                        int durability,
                        EnumMap<ArmorItem.Type, Integer> protectionFunctionForType,
                        int enchantmentValue,
                        SoundEvent sound,
                        float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durability = durability;
        this.protectionFunctionForType = protectionFunctionForType;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
        ;
    }

    // 耐久
    @Override
    public int getDurabilityForType(ArmorItem.Type p_266807_) {
        return durability;
    }

    // 护甲值
    @Override
    public int getDefenseForType(ArmorItem.Type p_267168_) {
        return protectionFunctionForType.get(p_267168_);
//        return 999;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return name;
    }

    // 护甲韧性
    @Override
    public float getToughness() {
        return toughness;
    }

    // 击退抗性
    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
