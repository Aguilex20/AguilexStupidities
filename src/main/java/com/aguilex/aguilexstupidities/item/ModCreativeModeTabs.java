package com.aguilex.aguilexstupidities.item;

import com.aguilex.aguilexstupidities.AguilexStupidities;
import com.aguilex.aguilexstupidities.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab AGUILEXSTUPIDITIES_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            new ResourceLocation(AguilexStupidities.MOD_ID, "aguilexstupidities_tab"),
            FabricItemGroup.builder().title(Component.translatable("creativemodetab.aguilexstupidities"))
                    .icon(() -> new ItemStack(ModBlocks.DURACELL_BATTERY)).displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SCREWDRIVER);
                        output.accept(ModItems.GEIGER_COUNTER);
                        output.accept(ModItems.SIREN_HORN);
                        output.accept(ModBlocks.DURACELL_BATTERY);
                        output.accept(ModBlocks.THUNDERBOLT_SIREN);
                        output.accept(ModBlocks.DEMON_CORE);
                    }).build());

    public static void registerCreativeModeTabs (){
        AguilexStupidities.LOGGER.info("Registering creative tabs for: " + AguilexStupidities.MOD_ID);
    }
}
