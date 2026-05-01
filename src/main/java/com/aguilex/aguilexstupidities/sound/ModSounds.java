package com.aguilex.aguilexstupidities.sound;

import com.aguilex.aguilexstupidities.AguilexStupidities;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    public static final SoundEvent THUNDERBOLT_SIREN = registerSoundEvent("thunderbolt_siren");
    public static final SoundEvent GEIGER_TICK = registerSoundEvent("geiger_tick");

    private static SoundEvent registerSoundEvent(String name){
        ResourceLocation id = new ResourceLocation(AguilexStupidities.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds(){
        AguilexStupidities.LOGGER.info("Registering sounds for: " + AguilexStupidities.MOD_ID);
    }
}
