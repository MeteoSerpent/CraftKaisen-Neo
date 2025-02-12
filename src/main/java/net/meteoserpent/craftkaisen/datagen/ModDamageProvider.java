package net.meteoserpent.craftkaisen.datagen;

import net.meteoserpent.craftkaisen.damage.ModDamageSources;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageProvider{

    protected static void bootstrap(BootstrapContext<DamageType> context)
    {
        context.register(ModDamageSources.CURSED_MILK, new DamageType("cursed_milk", 0.1F));
    }
}
