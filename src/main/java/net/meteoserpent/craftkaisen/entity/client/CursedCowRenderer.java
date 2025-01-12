package net.meteoserpent.craftkaisen.entity.client;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.entity.custom.CursedCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CursedCowRenderer extends GeoEntityRenderer<CursedCowEntity> {
    public CursedCowRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CursedCowModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CursedCowEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "textures/entity/cursed_spirits/cursed_cow.png");
    }
}
