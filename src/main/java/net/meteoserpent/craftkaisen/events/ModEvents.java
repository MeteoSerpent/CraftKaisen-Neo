package net.meteoserpent.craftkaisen.events;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.effects.ModEffects;
import net.meteoserpent.craftkaisen.util.ModTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = CraftKaisen.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onLivingEntityAttack(LivingIncomingDamageEvent event) {
        for (MobEffectInstance effectInstance: event.getEntity().getActiveEffects()) {
            if (effectInstance.getEffect().equals(ModEffects.INFINITY)) {
                if (event.getSource().is(DamageTypes.GENERIC_KILL)) {
                    return;
                }

                boolean bypass_infinity = false;

                if (event.getSource().getEntity() != null) {
                    ItemStack weaponItem = event.getSource().getEntity().getWeaponItem();
                    if (!weaponItem.isEmpty() && weaponItem.is(ModTags.Items.INFINITY_BYPASS)) {
                        bypass_infinity = true;
                    }
                }

                if (!bypass_infinity) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
