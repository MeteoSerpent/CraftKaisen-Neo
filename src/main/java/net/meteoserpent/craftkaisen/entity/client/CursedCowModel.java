package net.meteoserpent.craftkaisen.entity.client;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.entity.custom.CursedCowEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoRenderer;

public class CursedCowModel extends GeoModel<CursedCowEntity> {

    @Override
    public ResourceLocation getModelResource(CursedCowEntity animatable, @Nullable GeoRenderer<CursedCowEntity> renderer) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "geo/cursed_cow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CursedCowEntity animatable, @Nullable GeoRenderer<CursedCowEntity> renderer) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "textures/entity/cursed_spirits/cursed_cow.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CursedCowEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "animations/cursed_cow.animation.json");
    }

    @Override
    public void setCustomAnimations(CursedCowEntity animatable, long instanceId, AnimationState<CursedCowEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
