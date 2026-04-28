package com.aguilex.aguilexstupidities.block.entity;

import com.aguilex.aguilexstupidities.AguilexStupidities;
import com.aguilex.aguilexstupidities.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityType<DuracellBatteryBlockEntity> DURACELL_BATTERY_BLOCK_ENTITY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AguilexStupidities.MOD_ID, "duracell_battery_be"),
                    FabricBlockEntityTypeBuilder.create(DuracellBatteryBlockEntity::new,
                            ModBlocks.DURACELL_BATTERY).build());
    public static final BlockEntityType<ThunderboltSirenBlockEntity> THUNDERBOLT_SIREN_BLOCK_ENTITY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AguilexStupidities.MOD_ID, "thunderbolt_siren_be"),
                    FabricBlockEntityTypeBuilder.create(ThunderboltSirenBlockEntity::new,
                            ModBlocks.THUNDERBOLT_SIREN).build());

    public static void registerBlockEntities() {
        AguilexStupidities.LOGGER.info("Registering BlockEntities for" + AguilexStupidities.MOD_ID);
    }
}
