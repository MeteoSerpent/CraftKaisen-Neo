package net.meteoserpent.craftkaisen.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.meteoserpent.craftkaisen.CraftKaisen;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class CursedShaped implements CursedRecipe {
    public final ShapedRecipePattern pattern;

    public ItemStack getResult() {
        return result;
    }

    final ItemStack result;
    final String group;
    final CursedBookCategory category;

    public boolean isShowNotification() {
        return showNotification;
    }

    final boolean showNotification;
    @Nullable
    private PlacementInfo placementInfo;

    public ShapedRecipePattern pattern() {
        return pattern;
    }

    public CursedShaped(String pGroup, CursedBookCategory pCategory, ShapedRecipePattern shapedRecipePattern, ItemStack pResult, Boolean pShowNotification) {
        this.result = pResult;
        this.pattern = shapedRecipePattern;
        this.group = pGroup;
        this.category =pCategory;
        this.showNotification = pShowNotification;
    }

    public CursedShaped(String pGroup, CursedBookCategory pCategory, ShapedRecipePattern shapedRecipePattern, ItemStack pResult) {
        this(pGroup, pCategory, shapedRecipePattern, pResult, false);
    }


    @Override
    public boolean matches(CraftingInput input, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return null;
    }

    @Override
    public RecipeSerializer<? extends ShapedRecipe> getSerializer() {
        return RecipeSerializer.SHAPED_RECIPE;
    }

    @Override
    public CursedBookCategory category() {
        return null;
    }

    @Override
    public PlacementInfo placementInfo() {
        return null;
    }

    public static class Type implements RecipeType<CursedShaped> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "cursed_shaped";
    }

    public static class Serializer implements RecipeSerializer<CursedShaped> {
        public static final MapCodec<CursedShaped> CODEC = RecordCodecBuilder.mapCodec(
                p_340778_ -> p_340778_.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(CursedShaped::group),
                                CursedBookCategory.CODEC.fieldOf("category").forGetter(CursedShaped::category),
                                ShapedRecipePattern.MAP_CODEC.forGetter(CursedShaped::pattern),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(CursedShaped::getResult),
                                Codec.BOOL.optionalFieldOf("show_notification", Boolean.TRUE).forGetter(CursedShaped::isShowNotification)
                        )
                        .apply(p_340778_, CursedShaped::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, CursedShaped> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork, Serializer::fromNetwork
        );

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "cursed_shaped");

        @Override
        public MapCodec<CursedShaped> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CursedShaped> streamCodec() {
            return STREAM_CODEC;
        }

        private static CursedShaped fromNetwork(RegistryFriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            CursedBookCategory cursedbookcategory = buffer.readEnum(CursedBookCategory.class);
            ShapedRecipePattern shapedrecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            boolean flag = buffer.readBoolean();
            return new CursedShaped(s, cursedbookcategory, shapedrecipepattern, itemstack, flag);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, CursedShaped recipe) {
            buffer.writeUtf(recipe.group());
            buffer.writeEnum(recipe.category());
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.showNotification());
        }
    }
}