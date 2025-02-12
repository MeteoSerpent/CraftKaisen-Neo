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
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        ItemStack item = source.getWeaponItem();

        if (item != null && item.is(ModTags.Items.POSITIVE_ENERGY)) {
            damage *= 2;
        }

        return super.hurtServer(level, source, damage);
    }
}
