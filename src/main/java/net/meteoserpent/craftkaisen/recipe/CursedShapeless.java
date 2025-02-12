package net.meteoserpent.craftkaisen.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.meteoserpent.craftkaisen.CraftKaisen;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CursedShapeless implements CursedRecipe {

    final String group;
    final CursedBookCategory category;

    public ItemStack getResult() {
        return result;
    }

    final ItemStack result;

    final List<Ingredient> ingredients;
    @Nullable
    private PlacementInfo placementInfo;
    private final boolean isSimple;

    public CursedShapeless(String group, CursedBookCategory category, ItemStack result, List<Ingredient> ingredients) {
        this.group = group;
        this.category = category;
        this.result = result;
        this.ingredients = ingredients;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return RecipeSerializer.SHAPELESS_RECIPE;
    }

    @Override
    public CursedBookCategory category() {
        return this.category;
    }

    @Override
    public String group() {
        return this.group;
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.create(this.ingredients);
        }

        return this.placementInfo;
    }

    public boolean matches(CraftingInput input, Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else if (!isSimple) {
            var nonEmptyItems = new java.util.ArrayList<ItemStack>(input.ingredientCount());
            for (var item : input.items())
                if (!item.isEmpty())
                    nonEmptyItems.add(item);
            return net.neoforged.neoforge.common.util.RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
        } else {
            return input.size() == 1 && this.ingredients.size() == 1
                    ? this.ingredients.getFirst().test(input.getItem(0))
                    : input.stackedContents().canCraft(this, null);
        }
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
                new ShapelessCraftingRecipeDisplay(
                        this.ingredients.stream().map(Ingredient::display).toList(),
                        new SlotDisplay.ItemStackSlotDisplay(this.result),
                        new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)
                )
        );
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public static class Type implements RecipeType<CursedShapeless> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "cursed_shapeless";
    }

    public static class Serializer implements RecipeSerializer<CursedShapeless> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "cursed_shapeless");

        private static final MapCodec<CursedShapeless> CODEC = RecordCodecBuilder.mapCodec(
                p_360072_ -> p_360072_.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(p_301127_ -> p_301127_.group),
                                CursedBookCategory.CODEC.fieldOf("category").forGetter(p_301133_ -> p_301133_.category),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_301142_ -> p_301142_.result),
                                Codec.lazyInitialized(() -> Ingredient.CODEC.listOf(1, 3 * 3)).fieldOf("ingredients").forGetter(p_360071_ -> p_360071_.ingredients)
                        )
                        .apply(p_360072_, CursedShapeless::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, CursedShapeless> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, CursedShapeless::group,
                CursedBookCategory.STREAM_CODEC, CursedShapeless::category,
                ItemStack.STREAM_CODEC, CursedShapeless::getResult,
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), CursedShapeless::getIngredients,
                CursedShapeless::new
        );

        @Override
        public MapCodec<CursedShapeless> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CursedShapeless> streamCodec() {
            return STREAM_CODEC;
        }
    }
}