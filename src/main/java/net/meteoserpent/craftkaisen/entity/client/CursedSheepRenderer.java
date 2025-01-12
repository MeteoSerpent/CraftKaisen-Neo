package net.meteoserpent.craftkaisen.entity.client;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.entity.custom.CursedSheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CursedSheepRenderer extends GeoEntityRenderer<CursedSheepEntity> {
    public CursedSheepRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CursedSheepModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CursedSheepEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "textures/entity/cursed_spirits/sheep.png");
    }
}
