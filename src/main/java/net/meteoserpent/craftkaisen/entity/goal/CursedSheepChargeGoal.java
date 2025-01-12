package net.meteoserpent.craftkaisen.entity.goal;

import net.meteoserpent.craftkaisen.entity.custom.CursedSheepEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class CursedSheepChargeGoal extends Goal {

    private final CursedSheepEntity mob;
    private final float runSpeed;
    private final int dist;
    private final float walkSpeed;
    private int chargeDelay;
    private int timer = 0;
    private boolean moving;

    public CursedSheepChargeGoal(CursedSheepEntity cursedSheepEntity, float walkSpeed, float runSpeed, int dist) {
        this.mob = cursedSheepEntity;
        this.runSpeed = runSpeed;
        this.walkSpeed = walkSpeed;
        this.dist = dist;
    }

    @Override
    public boolean canUse() {
        return this.isValidTarget() && !this.mob.isInWater();
    }

    public boolean canContinueToUse() {
        return (this.canUse() && !this.mob.getNavigation().isDone()) || this.mob.isCharging();
    }

    public void start() {
        super.start();
        this.mob.setAggressive(true);
        this.chargeDelay = 20;
        this.timer = 0;
        this.moving = false;
        this.mob.setChasing(true);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        this.timer = Math.max(this.timer-1, 0);

        LivingEntity entity = this.mob.getTarget();

        if (haveEntity(entity)) {

            moveToTarget(entity);

            if(entityInRange(entity)) {

                if (timeToStart()) {
                    timerStart(35);
                    this.mob.setIsCharging(true);
                }

                if (chargeTime()) {
                    charge(entity);
                    hitEntity(entity);
                }

                if (endTime()) {
                    end();
                }

                if (this.timer <= 0) {
                    this.mob.setIsCharging(false);
                    timerStart(chargeDelay);
                }

            }
            else {
                reset();
            }

        }
        else {
            reset();
        }
    }

    private void reset() {
        this.mob.setIsCharging(false);
        moving = false;
    }

    private void moveToTarget(LivingEntity entity) {
        if (charging()) {return;}

        this.mob.getNavigation().moveTo(entity, walkSpeed);
    }

    private void end() {
        this.mob.getNavigation().stop();
        moving = false;
    }

    private void hitEntity(LivingEntity entity) {
        if (this.mob.getBoundingBox().intersects(entity.getBoundingBox())) {
            this.mob.doHurtTarget(getServerLevel(entity.level()), entity);
            this.mob.setIsCharging(false);
            this.moving = false;


            Vec3 vec3 = entity.position();
            vec3 = vec3.subtract(this.mob.position());
            vec3 = vec3.normalize();
            vec3 = vec3.add(0, -1, 0);

            this.mob.setDeltaMovement(vec3.scale(-0.5));

            timerStart(chargeDelay);
        }
    }

    private void charge(LivingEntity entity) {
        if (moving) {return;}

        Vec3 velocity = getVelocity(entity).scale(5);

        Vec3 vec3 = entity.position().add(velocity);
        vec3 = vec3.subtract(this.mob.position());
        vec3 = vec3.normalize();
        vec3 = vec3.scale(8);
        vec3 = this.mob.position().add(vec3);

        this.mob.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, runSpeed);
        this.moving = true;
    }

    private Vec3 getVelocity(LivingEntity entity) {
        double deltaX = entity.getX() - entity.xOld;
        double deltaY = entity.getY() - entity.yOld;
        double deltaZ = entity.getZ() - entity.zOld;

        return new Vec3(deltaX, deltaY, deltaZ);
    }

    private boolean endTime() {
        return this.timer <= 10 && charging();
    }

    private boolean chargeTime() {
        return this.timer <= 30 && charging();
    }

    private boolean charging() {
        return this.mob.isCharging();
    }

    private void timerStart(int i) {
        this.timer = adjustedTickDelay(i);
    }

    private boolean timeToStart() {
        return this.timer <= 0 && !charging();
    }

    private boolean entityInRange(LivingEntity entity) {
        return this.mob.distanceTo(entity) < dist;
    }

    private boolean haveEntity(LivingEntity entity) {
        return entity != null;
    }


    private boolean isValidTarget() {
        return this.mob.getTarget() != null && this.mob.getTarget().isAlive();
    }

    @Override
    public void stop() {
        this.mob.setIsCharging(false);
        this.mob.setChasing(false);
        super.stop();
    }
}
