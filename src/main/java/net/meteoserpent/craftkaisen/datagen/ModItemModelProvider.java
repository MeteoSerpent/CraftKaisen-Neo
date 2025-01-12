package net.meteoserpent.craftkaisen.datagen;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.blocks.ModBlocks;
import net.meteoserpent.craftkaisen.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, CraftKaisen.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SPIRIT_ORB.get());
        basicItem(ModItems.DISASTER_FLAMES.get());
        basicItem(ModItems.SIX_EYES.get());

        sukunaFingerItem();

        withExistingParent(ModItems.CURSED_COW_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.CURSED_SHEEP_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder sukunaFingerItem() {
        return withExistingParent(ModBlocks.SUKUNA_FINGER.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID,"item/"+ ModBlocks.SUKUNA_FINGER.getId().getPath()));
    }
}