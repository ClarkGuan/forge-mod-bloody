package com.clark.mod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ClarkMod.MODID)
public class ClarkMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "clarkmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    // 无尽之刃
    public static final RegistryObject<Item> BLOOD_SWORD_ITEM = ITEMS.register("bloody_sword", BloodySwordItem::new);
    // 斧子
    public static final RegistryObject<Item> BLOODY_AXE = ITEMS.register("bloody_axe", () -> new AxeItem(BloodyItem.Holder.getInstance(), 990.0F, 6.0F, new Item.Properties()));
    // 锄头
    public static final RegistryObject<Item> BLOODY_HOE = ITEMS.register("bloody_hoe", () -> new HoeItem(BloodyItem.Holder.getInstance(), -3, 0.0F, new Item.Properties()));
    // 镐子
    public static final RegistryObject<Item> BLOODY_PICKAXE = ITEMS.register("bloody_pickaxe", () -> new PickaxeItem(BloodyItem.Holder.getInstance(), 1, -2.8F, new Item.Properties()));
    // 铲子
    public static final RegistryObject<Item> BLOODY_SHOVEL = ITEMS.register("bloody_shovel", () -> new ShovelItem(BloodyItem.Holder.getInstance(), 1.5F, -3.0F, new Item.Properties()));
    // 靴子
    public static final RegistryObject<Item> BLOODY_BOOTS = ITEMS.register("bloody_boots", () -> new ArmorItem(BloodyArmorMaterial.BLOODYITEM, ArmorItem.Type.BOOTS, new Item.Properties()));
    // 护腿
    public static final RegistryObject<Item> BLOODY_LEGGINGS = ITEMS.register("bloody_leggings", () -> new ArmorItem(BloodyArmorMaterial.BLOODYITEM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    // 胸甲
    public static final RegistryObject<Item> BLOODY_CHESTPLATE = ITEMS.register("bloody_chestplate", () -> new ArmorItem(BloodyArmorMaterial.BLOODYITEM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    // 头盔
    public static final RegistryObject<Item> BLOODY_HELMET = ITEMS.register("bloody_helmet", () -> new ArmorItem(BloodyArmorMaterial.BLOODYITEM, ArmorItem.Type.HELMET, new Item.Properties()));
    // 鞘翅
    public static final RegistryObject<Item> BLOODY_ELYTRA = ITEMS.register("bloody_elytra", () -> new ElytraItem((new Item.Properties()).durability(99999999).rarity(Rarity.UNCOMMON)));
    // 剪刀
    public static final RegistryObject<Item> BLOODY_SHEARS = ITEMS.register("bloody_shears", () -> new ShearsItem((new Item.Properties()).durability(99999999)));
//    public static final RegistryObject<Item> BLOODY_SHIELD = ITEMS.register("bloody_shield", () -> new ShieldItem((new Item.Properties()).durability(99999999)));
//    public static final RegistryObject<Item> BLOODY_TRIDENT = ITEMS.register("bloody_trident", () -> new TridentItem((new Item.Properties()).durability(99999999)));

    public ClarkMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
