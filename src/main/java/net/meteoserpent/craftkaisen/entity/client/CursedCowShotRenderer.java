package net.meteoserpent.craftkaisen.entity.client;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.entity.projectile.CursedCowShot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CursedCowShotRenderer extends GeoEntityRenderer<CursedCowShot> {

    public CursedCowShotRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CursedCowShotModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CursedCowShot animatable) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "textures/entity/cursed_spirits/cursed_milk.png");
    }

}
