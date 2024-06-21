package net.gradient.magicalworld.menus;

import net.gradient.magicalworld.MagicalWorld;
import net.gradient.magicalworld.blocks.MWBlocks;
import net.gradient.magicalworld.items.MWItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MWCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MagicalWorld.MODID);

    public static final RegistryObject<CreativeModeTab> MW_CREATIVE_TAB = CREATIVE_MODE_TABS.register("magicalworld_ctab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MWItems.TOMATO.get()))
                    .title(Component.translatable("creativetab.magicalworld_ctab"))

                    //Index stuff
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(MWItems.TOMATO.get());
                        output.accept(MWItems.TOMATO_PASTE.get());

                        output.accept(MWBlocks.FROZEN_TOMATO_PASTE_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
