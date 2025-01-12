package net.meteoserpent.craftkaisen.blocks;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.blocks.custom.CursedTechniqueCrafterBlock;
import net.meteoserpent.craftkaisen.blocks.custom.SukunaFingerBlock;
import net.meteoserpent.craftkaisen.items.ModFoodProperties;
import net.meteoserpent.craftkaisen.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CraftKaisen.MODID);

    public static final DeferredBlock<Block> SUKUNA_FINGER = registerBlock("sukuna_finger",
            () -> new SukunaFingerBlock(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "sukuna_finger")))
                    .sound(SoundType.FUNGUS).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> CURSED_TECHNIQUE_CRAFTER = registerBlock("cursed_technique_crafter",
            /*() -> new CursedTechniqueCrafterBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion())*/
            () -> new CursedTechniqueCrafterBlock(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "cursed_technique_crafter")))
                    .sound(SoundType.STONE).noOcclusion()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        if (name.equals("sukuna_finger")) {
            registerSukunaItem(name, toReturn);
        }
        else {
            registerBlockItem(name, toReturn);
        }
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, name)))));
    }

    private static <T extends Block> void registerSukunaItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().food(ModFoodProperties.SUKUNA_FINGER, ModFoodProperties.SUKUNA_FINGER_EFFECT).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "sukuna_finger")))) {
            @Override
            public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                tooltipComponents.add(Component.translatable("tooltip.craftkaisen.sukuna_finger"));
                super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
            }
        });
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
