package net.meteoserpent.craftkaisen.entity.goal;

import net.meteoserpent.craftkaisen.entity.custom.CursedCowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class CursedCowShootGoal extends Goal {

    private final CursedCowEntity mob;
    private final float walkSpeed;
    private final int dist;
    private int timer;
    private int sightTime;
    private boolean shot;

    public CursedCowShootGoal(CursedCowEntity cursedCowEntity, float walkSpeed, int dist) {
        this.mob = cursedCowEntity;
        this.walkSpeed = walkSpeed;
        this.dist = dist;
    }

    @Override
    public boolean canUse() {
        return isValidTarget(this.mob.getTarget());
    }

    @Override
    public boolean canContinueToUse() {
        return (canUse() && !this.mob.getNavigation().isDone()) || this.mob.isAttacking();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void start() {
        super.start();
        this.mob.setAggressive(true);
        this.mob.setChasing(true);
        this.shot = false;
    }

    @Override
    public void tick() {
        this.timer = Math.max(this.timer -1, 0);
        LivingEntity entity = this.mob.getTarget();
        if (isValidTarget(entity)) {

            lookAtTarget(entity);
            this.sightTime = this.mob.hasLineOfSight(entity) ? this.sightTime+1 : 0;
            if (inAttackRange(entity)) {
                
                if (timeToStart()) {
                    timerStart(30);
                    this.mob.setAttacking(true);
                    this.mob.getNavigation().stop();
                }
                else if (timeToShoot()) {
                    this.mob.performRangedAttack(entity, 0);
                    this.shot = true;
                }
                else if (this.timer <= 0) {
                    timerStart(40);
                    this.mob.setAttacking(false);
                    this.shot = false;
                }
                else if (!this.mob.isAttacking()){
                    moveTo(entity);
                }
            }
            else {
                moveTo(entity);
            }
        }
    }

    private void moveTo(LivingEntity entity) {
        this.mob.getNavigation().moveTo(entity, walkSpeed);
    }

    private boolean timeToShoot() {
        return this.timer <= 10 && this.mob.isAttacking() && !this.shot;
    }

    private void timerStart(int time) {
        this.timer = adjustedTickDelay(time);
    }

    private boolean timeToStart() {
        return this.timer <= 0 && !this.mob.isAttacking() && sightTime >= 5;
    }

    private boolean inAttackRange(LivingEntity entity) {
        return (this.mob.distanceTo(entity) < dist) || this.mob.isAttacking();
    }

    private void lookAtTarget(LivingEntity entity) {
        this.mob.getLookControl().setLookAt(entity);
    }

    private boolean isValidTarget(LivingEntity entity) {
        return entity != null && entity.isAlive();
    }

    @Override
    public void stop() {
        this.mob.setAggressive(false);
        this.mob.setChasing(false);
        super.stop();
    }
}
