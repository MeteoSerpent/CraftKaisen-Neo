package net.meteoserpent.craftkaisen.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SukunaFingerBlockEntity/* extends BlockEntity*/ {

//    public SukunaFingerBlockEntity( BlockPos pPos, BlockState pBlockState) {
//        super(ModBlockEntities.SUKUNA_FINGER.get(), pPos, pBlockState);
//    }
//
//    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
//        List<CursedSpiritEntity> cursed_spirits = level.getEntitiesOfClass(CursedSpiritEntity.class, new AABB(blockPos).inflate(20));
//
//        for (CursedSpiritEntity spirit: cursed_spirits) {
//
//            Vec3 pos = spirit.position();
//
//            Vec3 dir = pos.subtract(blockPos.getCenter()).normalize();
//            dir = new Vec3(dir.x, dir.y, dir.z);
//            spirit.addDeltaMovement(dir);
//            spirit.hurt(new DamageSources(level.registryAccess()).magic(), 2);
//        }
//    }

}
