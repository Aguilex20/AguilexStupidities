package com.aguilex.aguilexstupidities.block;

import com.aguilex.aguilexstupidities.AguilexStupidities;
import com.aguilex.aguilexstupidities.block.custom.DuracellBatteryBlock;
import com.aguilex.aguilexstupidities.block.custom.ThunderboltSirenBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

    public static final Block DURACELL_BATTERY = registerBlock("duracell_battery",
            new DuracellBatteryBlock(BlockBehaviour.Properties.of().strength(1.0F, 0.25F).requiresCorrectToolForDrops()));
    public static final Block THUNDERBOLT_SIREN = registerBlock("thunderbolt_siren",
            new ThunderboltSirenBlock(BlockBehaviour.Properties.of().strength(1.0F, 3.0F).requiresCorrectToolForDrops().noOcclusion()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(AguilexStupidities.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AguilexStupidities.MOD_ID, name),
                new BlockItem(block, new Item.Properties()));
    }

    public static void registerModBlocks(){
        AguilexStupidities.LOGGER.info("Registering mod blocks for: " + AguilexStupidities.MOD_ID);
    }
}
