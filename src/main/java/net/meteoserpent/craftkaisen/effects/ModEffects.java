package net.meteoserpent.craftkaisen.effects;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, CraftKaisen.MODID);

    public static final Holder<MobEffect> INFINITY = MOB_EFFECTS.register("infinity",
            () -> new InfinityEffect(MobEffectCategory.BENEFICIAL, 0x19c0cf));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
