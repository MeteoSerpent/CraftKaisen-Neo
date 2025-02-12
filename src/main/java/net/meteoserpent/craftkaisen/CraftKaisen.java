package net.meteoserpent.craftkaisen;

import net.meteoserpent.craftkaisen.blocks.ModBlocks;
import net.meteoserpent.craftkaisen.effects.ModEffects;
import net.meteoserpent.craftkaisen.entity.ModEntities;
import net.meteoserpent.craftkaisen.entity.client.CursedCowRenderer;
import net.meteoserpent.craftkaisen.entity.client.CursedCowShotRenderer;
import net.meteoserpent.craftkaisen.entity.client.CursedSheepRenderer;
import net.meteoserpent.craftkaisen.items.ModCreativeModeTabs;
import net.meteoserpent.craftkaisen.items.ModItems;
import net.meteoserpent.craftkaisen.recipe.ModRecipeCategories;
import net.meteoserpent.craftkaisen.recipe.ModRecipes;
import net.meteoserpent.craftkaisen.screen.ModMenuTypes;
import net.meteoserpent.craftkaisen.sounds.ModSounds;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CraftKaisen.MODID)
public class CraftKaisen
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "craftkaisen";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CraftKaisen(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModEffects.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModRecipeCategories.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.CURSED_SHEEP.get(), CursedSheepRenderer::new);
            EntityRenderers.register(ModEntities.CURSED_COW.get(), CursedCowRenderer::new);
            EntityRenderers.register(ModEntities.CURSED_COW_SHOT.get(), CursedCowShotRenderer::new);

        }
    }
}
