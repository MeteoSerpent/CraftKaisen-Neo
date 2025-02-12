package net.meteoserpent.craftkaisen.entity;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.meteoserpent.craftkaisen.entity.custom.CursedCowEntity;
import net.meteoserpent.craftkaisen.entity.custom.CursedSheepEntity;
import net.meteoserpent.craftkaisen.entity.projectile.CursedCowShot;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE ,CraftKaisen.MODID);

    public static final Supplier<EntityType<CursedCowEntity>> CURSED_COW =
            ENTITY_TYPES.register("cursed_cow",
                    () -> EntityType.Builder.of(CursedCowEntity::new, MobCategory.MONSTER)
                            .sized(1F, 2.3F).
                            build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "cursed_cow"))));

    public static final Supplier<EntityType<CursedSheepEntity>> CURSED_SHEEP =
            ENTITY_TYPES.register("cursed_sheep",
                    () -> EntityType.Builder.of(CursedSheepEntity::new, MobCategory.MONSTER)
                            .sized(0.9F, 1.3F)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "cursed_sheep"))));

    public static final Supplier<EntityType<CursedCowShot>> CURSED_COW_SHOT =
            ENTITY_TYPES.register("cursed_cow_shot",
                    () -> EntityType.Builder.of(CursedCowShot::new, MobCategory.MISC)
                            .sized(0.25f,0.25f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, "cursed_cow_shot"))));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
