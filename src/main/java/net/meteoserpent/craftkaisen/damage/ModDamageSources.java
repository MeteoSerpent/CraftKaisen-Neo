package net.meteoserpent.craftkaisen.damage;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageSources {

    public static final ResourceKey<DamageType> CURSED_MILK = register("cursed_milk");

    private static ResourceKey<DamageType> register(String name)
    {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, name));
    }
}
