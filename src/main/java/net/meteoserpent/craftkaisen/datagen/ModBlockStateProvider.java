package net.meteoserpent.craftkaisen.datagen;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CraftKaisen.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.CURSED_TECHNIQUE_CRAFTER.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/cursed_technique_crafter")));
        simpleBlock(ModBlocks.SUKUNA_FINGER.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/sukuna_finger")));
    }


    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
