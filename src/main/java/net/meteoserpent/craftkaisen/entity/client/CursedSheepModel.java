package net.meteoserpent.craftkaisen.entity.client;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.entity.custom.CursedSheepEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoRenderer;

public class CursedSheepModel extends GeoModel<CursedSheepEntity> {

    @Override
    public ResourceLocation getModelResource(CursedSheepEntity animatable, @Nullable GeoRenderer<CursedSheepEntity> renderer) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "geo/cursed_sheep.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CursedSheepEntity animatable, @Nullable GeoRenderer<CursedSheepEntity> renderer) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "geo/cursed_sheep.geo.json");
    }

    @Override
    public ResourceLocation getAnimationResource(CursedSheepEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "animations/cursed_sheep.animation.json");
    }

    @Override
    public void setCustomAnimations(CursedSheepEntity animatable, long instanceId, AnimationState<CursedSheepEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
