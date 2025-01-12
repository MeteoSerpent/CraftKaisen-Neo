package net.meteoserpent.craftkaisen.util;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_CURSED_TOOL = tag("needs_cursed_tool");
        public static final TagKey<Block> INCORRECT_FOR_CURSED_TOOL = tag("incorrect_for_cursed_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CURSED_TECHNIQUE = tag("cursed_technique");
        public static final TagKey<Item> POSITIVE_ENERGY = tag("positive_energy");
        public static final TagKey<Item> INFINITY_BYPASS = tag("infinity_bypass");
        public static final TagKey<Item> BLANK = tag("blank");


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, name));
        }
    }
}
