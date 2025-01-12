package net.meteoserpent.craftkaisen.entity.custom;


import net.meteoserpent.craftkaisen.entity.goal.CursedSheepChargeGoal;
import net.meteoserpent.craftkaisen.sounds.ModSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;


public class CursedSheepEntity extends CursedSpiritEntity implements GeoEntity {
    protected static final EntityDataAccessor<Boolean> CHARGING =
            SynchedEntityData.defineId(CursedSheepEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CHASING =
            SynchedEntityData.defineId(CursedSheepEntity.class, EntityDataSerializers.BOOLEAN);

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private double currentAnimTime;

    public CursedSheepEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Monster.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ATTACK_SPEED, 4)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(2,new CursedSheepChargeGoal(this, 1.4f, 3f, 7));
        this.goalSelector.addGoal(3,new WaterAvoidingRandomStrollGoal(this,1));
        this.goalSelector.addGoal(5,new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
    }

    public boolean isCharging() {
        return this.entityData.get(CHARGING);
    }

    public boolean isChasing() {
        return this.entityData.get(CHASING);
    }

    public void setIsCharging(boolean pCharging) {
        this.entityData.set(CHARGING, pCharging);
    }

    public void setChasing(boolean pChasing) {
        this.entityData.set(CHASING, pChasing);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            if (isChasing()) {
                state.getController().setAnimationSpeed(1.4f);
            }
            else {
                state.getController().setAnimationSpeed(1f);
            }

            if (isCharging()) {
                state.setAnimation(RawAnimation.begin().then("animation.sheep.roll", Animation.LoopType.LOOP));
            }
            else {
                if (state.isMoving()) {
                    state.setAnimation(RawAnimation.begin().then("animation.sheep.walk", Animation.LoopType.LOOP));
                } else {
                    state.setAnimation(RawAnimation.begin().then("animation.model.idle", Animation.LoopType.LOOP));
                }
            }

            this.currentAnimTime = state.animationTick;

            return PlayState.CONTINUE;
        }));
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.CURSED_SHEEP_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.CURSED_SHEEP_DEATH.get();
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.CURSED_SHEEP_SAY.get();
    }

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5f;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CHARGING, false);
        builder.define(CHASING, false);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
