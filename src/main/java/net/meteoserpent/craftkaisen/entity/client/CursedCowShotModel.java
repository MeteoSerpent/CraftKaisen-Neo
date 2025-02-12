package net.meteoserpent.craftkaisen.entity.client;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.entity.projectile.CursedCowShot;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class CursedCowShotModel extends GeoModel<CursedCowShot> {

    @Override
    public ResourceLocation getModelResource(CursedCowShot animatable, @Nullable GeoRenderer<CursedCowShot> renderer) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "geo/cursed_milk.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CursedCowShot animatable, @Nullable GeoRenderer<CursedCowShot> renderer) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "textures/entity/cursed_spirits/cursed_milk.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CursedCowShot animatable) {
        return null;
    }
}
