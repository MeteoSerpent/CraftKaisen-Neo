package net.meteoserpent.craftkaisen.recipe;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeCategories {
    public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORY =
            DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, CraftKaisen.MODID);

    public static final Supplier<RecipeBookCategory> CURSED_TECHNIQUE = RECIPE_BOOK_CATEGORY.register(
            "cursed_technique", RecipeBookCategory::new
    );

    public static final Supplier<RecipeBookCategory> BINDING_VOW = RECIPE_BOOK_CATEGORY.register(
            "binding_vow", RecipeBookCategory::new
    );

    public static final Supplier<RecipeBookCategory> TECHNIQUE_UPGRADE = RECIPE_BOOK_CATEGORY.register(
            "technique_upgrade", RecipeBookCategory::new
    );

    public static void register(IEventBus eventBus) {
        RECIPE_BOOK_CATEGORY.register(eventBus);
    }
}
