package net.gradient.magicalworld;

import com.mojang.logging.LogUtils;
import net.gradient.magicalworld.blocks.MWBlocks;
import net.gradient.magicalworld.menus.MWCreativeTab;
import net.gradient.magicalworld.items.MWItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MagicalWorld.MODID)
public class MagicalWorld
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "magicalworld";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab

    public MagicalWorld()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        MWCreativeTab.register(modEventBus);
        MWBlocks.register(modEventBus);
        MWItems.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);



        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("Mod has been successfully loaded");


    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(MWItems.TOMATO);

        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(MWItems.TOMATO_PASTE);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {


        @SubscribeEvent
        public void onInitialize() {
            GeckoLib.initialize();
            LOGGER.info("Mod is running");
            LOGGER.info("loading");
        }
    }
}
