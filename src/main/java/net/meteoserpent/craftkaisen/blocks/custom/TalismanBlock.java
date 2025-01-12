package net.meteoserpent.craftkaisen.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class TalismanBlock extends Block {
    public TalismanBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof Player) {
            ((Player) pEntity).addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100));
        } else if (pEntity instanceof Monster){
            Vec3 raw_dir = pEntity.getDeltaMovement();
            Vec2 dir = new Vec2((float) raw_dir.x, (float) raw_dir.z).normalized();

            pEntity.moveTo(new Vec3(pPos.getX()-(dir.x), pPos.getY()+1, pPos.getZ()-(dir.y)));
        }
    }


}
