package net.meteoserpent.craftkaisen.items.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class FuelItem extends Item {
    private final int burn_time;

    public FuelItem(Properties pProperties, int burn_time) {
        super(pProperties);
        this.burn_time = burn_time;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burn_time;
    }
}
