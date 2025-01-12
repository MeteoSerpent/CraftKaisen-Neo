package net.meteoserpent.craftkaisen.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties SUKUNA_FINGER = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .alwaysEdible().fast().effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 2), 1).build();
}
