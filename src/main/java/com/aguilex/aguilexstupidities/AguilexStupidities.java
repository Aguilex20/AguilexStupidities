package com.aguilex.aguilexstupidities;

import com.aguilex.aguilexstupidities.block.ModBlocks;
import com.aguilex.aguilexstupidities.block.entity.ModBlockEntities;
import com.aguilex.aguilexstupidities.item.ModCreativeModeTabs;
import com.aguilex.aguilexstupidities.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AguilexStupidities implements ModInitializer {

    public static final String MOD_ID = "aguilexstupidities";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModCreativeModeTabs.registerCreativeModeTabs();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModSounds.registerSounds();
        //ModItems.registerModItems();
    }
}
