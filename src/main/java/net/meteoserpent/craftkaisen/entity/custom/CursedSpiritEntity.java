package net.meteoserpent.craftkaisen.entity.custom;

import net.meteoserpent.craftkaisen.util.ModTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CursedSpiritEntity extends Monster {

    protected CursedSpiritEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void actuallyHurt(ServerLevel p_376745_, DamageSource pSource, float pAmount) {
        ItemStack item = pSource.getWeaponItem();

        if (item != null && item.is(ModTags.Items.POSITIVE_ENERGY)) {
            pAmount *= 2;
        }

        super.actuallyHurt(p_376745_, pSource, pAmount);
    }
}
