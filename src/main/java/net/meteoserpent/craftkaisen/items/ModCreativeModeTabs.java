package net.meteoserpent.craftkaisen.items;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CraftKaisen.MODID);

    public static final Supplier<CreativeModeTab> CRAFT_KAISEN_TAB = CREATIVE_MODE_TABS.register("craft_kaisen_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SIX_EYES.get()))
                    .title(Component.translatable("creativetab.craftkaisen.craft_kaisen_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SIX_EYES.get());
                        output.accept(ModItems.DISASTER_FLAMES.get());
                        output.accept(ModItems.INVERTED_SPEAR_OF_HEAVEN.get());
                        output.accept(ModItems.SWORD_OF_EXTERMINATION.get());
                        output.accept(ModItems.SPIRIT_ORB.get());
//                        output.accept(ModItems.CURSED_COW_EGG.get());
//                        output.accept(ModItems.CURSED_SHEEP_EGG.get());

                        output.accept(ModBlocks.CURSED_TECHNIQUE_CRAFTER.get());
                        output.accept(ModBlocks.SUKUNA_FINGER.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
