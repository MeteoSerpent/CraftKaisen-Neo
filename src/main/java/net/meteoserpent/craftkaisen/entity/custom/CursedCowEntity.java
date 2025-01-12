package net.meteoserpent.craftkaisen.entity.custom;


import net.meteoserpent.craftkaisen.entity.goal.CursedCowAttackGoal;
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


public class CursedCowEntity extends CursedSpiritEntity implements GeoEntity {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(CursedCowEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> CHASING =
            SynchedEntityData.defineId(CursedCowEntity.class, EntityDataSerializers.BOOLEAN);

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public CursedCowEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.ATTACK_SPEED, 1)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(2,new CursedCowAttackGoal(this,1.2, true));
        this.goalSelector.addGoal(3,new WaterAvoidingRandomStrollGoal(this,1));
        this.goalSelector.addGoal(5,new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            if (isChasing() && !isAttacking()) {
                state.getController().setAnimationSpeed(1.2f);
            }
            else {
                state.getController().setAnimationSpeed(1f);
            }

            if (isAttacking()) {
                state.setAnimation(RawAnimation.begin().then("animation.unknown.attack", Animation.LoopType.LOOP));
            }else if (state.isMoving()) {
                state.setAnimation(RawAnimation.begin().then("animation.model.walk", Animation.LoopType.LOOP));
            } else {
                state.setAnimation(RawAnimation.begin().then("animation.model.idle", Animation.LoopType.LOOP));
            }


            return PlayState.CONTINUE;
        }));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.CURSED_COW_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.CURSED_COW_DEATH.get();
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.CURSED_COW_SAY.get();
    }

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5f;
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setChasing(boolean chasing) {
        this.entityData.set(CHASING, chasing);
    }

    public boolean isChasing() {
        return this.entityData.get(CHASING);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
        builder.define(CHASING, false);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
