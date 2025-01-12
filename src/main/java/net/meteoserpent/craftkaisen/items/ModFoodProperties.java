package net.meteoserpent.craftkaisen.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodProperties {
    public static final FoodProperties SUKUNA_FINGER = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .alwaysEdible().build();

    public static final Consumable SUKUNA_FINGER_EFFECT =Consumables.defaultFood().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 2), 1)).build();
}
