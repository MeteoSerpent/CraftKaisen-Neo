package net.meteoserpent.craftkaisen.recipe;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, CraftKaisen.MODID);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, CraftKaisen.MODID);

    public static final Supplier<CursedShaped.Serializer> CURSED_SHAPED =
            SERIALIZERS.register("cursed_shaped", () -> CursedShaped.Serializer.INSTANCE);
    public static final Supplier<CursedShapeless.Serializer> CURSED_SHAPELESS =
            SERIALIZERS.register("cursed_shapeless", () -> CursedShapeless.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }


    public static final Supplier<RecipeType<CursedRecipe>> CURSED_RECIPE =
            RECIPE_TYPES.register(
                    "cursed_recipe",
                    // We need the qualifying generic here due to generics being generics.
                    registryName -> new RecipeType<>() {
                        @Override
                        public String toString() {
                            return registryName.toString();
                        }
                    }
                    );
}
