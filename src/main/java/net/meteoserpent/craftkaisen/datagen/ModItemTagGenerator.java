package net.meteoserpent.craftkaisen.datagen;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.items.ModItems;
import net.meteoserpent.craftkaisen.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> pBlockTags, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, CraftKaisen.MODID, existingFileHelper);

    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.CURSED_TECHNIQUE)
                .add(ModItems.DISASTER_FLAMES.get());
        this.tag(ModTags.Items.INFINITY_BYPASS)
                .add(ModItems.INVERTED_SPEAR_OF_HEAVEN.get());
        this.tag(ModTags.Items.POSITIVE_ENERGY)
                .add(ModItems.SWORD_OF_EXTERMINATION.get());
    }
}
