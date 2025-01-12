package net.meteoserpent.craftkaisen.sounds;

import net.meteoserpent.craftkaisen.CraftKaisen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, CraftKaisen.MODID);

    public static final Supplier<SoundEvent> CURSED_COW_HURT = registerSoundEvent("cursed_cow_hurt");
    public static final Supplier<SoundEvent> CURSED_COW_DEATH = registerSoundEvent("cursed_cow_death");
    public static final Supplier<SoundEvent> CURSED_COW_SAY = registerSoundEvent("cursed_cow_say");

    public static final Supplier<SoundEvent> CURSED_SHEEP_HURT = registerSoundEvent("cursed_sheep_hurt");
    public static final Supplier<SoundEvent> CURSED_SHEEP_DEATH = registerSoundEvent("cursed_sheep_death");
    public static final Supplier<SoundEvent> CURSED_SHEEP_SAY = registerSoundEvent("cursed_sheep_say");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CraftKaisen.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
