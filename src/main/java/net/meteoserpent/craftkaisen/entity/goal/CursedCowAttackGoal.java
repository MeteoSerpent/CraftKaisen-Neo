package net.meteoserpent.craftkaisen.entity.goal;

import net.meteoserpent.craftkaisen.entity.custom.CursedCowEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class CursedCowAttackGoal extends MeleeAttackGoal {
    private final CursedCowEntity entity;
    private int attackDelay;
    private int untilNextAttack;
    private boolean shouldCountTillNextAttack;

    public CursedCowAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.entity = ((CursedCowEntity) pMob);
    }

    @Override
    public void start() {
        super.start();
        this.entity.setChasing(true);
        this.attackDelay = 5;
        this.untilNextAttack = 5;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
        if (isEnemyWithinAttackDistance(target)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            if(isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ());
                performAttack(target);
                entity.setAttacking(false);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
        return this.mob.isWithinMeleeAttackRange(pEnemy);
    }

    protected void resetAttackCooldown() {

        this.untilNextAttack = this.adjustedTickDelay(15);
    }

    protected boolean isTimeToAttack() {
        return this.untilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.untilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.untilNextAttack;
    }

    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(getServerLevel(pEnemy.level()), pEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        
        if (this.shouldCountTillNextAttack) {
            this.untilNextAttack = Math.max(this.untilNextAttack-1, 0);
        }
    }

    @Override
    public void stop() {
        this.entity.setAttacking(false);
        this.entity.setChasing(false);
        super.stop();
    }
}
