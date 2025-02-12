package net.meteoserpent.craftkaisen.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public interface CursedRecipe extends Recipe<CraftingInput> {

    @Override
    RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer();

    CursedBookCategory category();

    @Override
    default RecipeType<? extends Recipe<CraftingInput>> getType() {
        return ModRecipes.CURSED_RECIPE.get();
    }

    default NonNullList<ItemStack> getRemainingItems(CraftingInput p_380110_) {
        return defaultCraftingReminder(p_380110_);
    }

    static NonNullList<ItemStack> defaultCraftingReminder(CraftingInput p_380223_) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(p_380223_.size(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); i++) {
            ItemStack item = p_380223_.getItem(i);
            nonnulllist.set(i, item.getCraftingRemainder());
        }

        return nonnulllist;
    }

    @Override
    default RecipeBookCategory recipeBookCategory() {
        return switch (this.category()) {
            case TECHNIQUE -> ModRecipeCategories.CURSED_TECHNIQUE.get();
            case BINDING_VOW -> ModRecipeCategories.BINDING_VOW.get();
            case UPGRADE -> ModRecipeCategories.TECHNIQUE_UPGRADE.get();
        };
    }

}
