package com.aguilex.aguilexstupidities.item;

import com.aguilex.aguilexstupidities.AguilexStupidities;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {
    private static Item registerItem(String name, Item item){
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AguilexStupidities.MOD_ID, name), item);
    }

    //Function called from Aguilexht.java to load items
    public static void registerModItems(){
        AguilexStupidities.LOGGER.info("Registering mod items for: " + AguilexStupidities.MOD_ID);
    }
}
