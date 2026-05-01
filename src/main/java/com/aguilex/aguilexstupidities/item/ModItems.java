package com.aguilex.aguilexstupidities.item;

import com.aguilex.aguilexstupidities.AguilexStupidities;
import com.aguilex.aguilexstupidities.item.custom.ItemWithTooltip;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Item SIREN_HORN = registerItem("siren_horn", new Item(new Item.Properties()));
    public static final Item GEIGER_COUNTER = registerItem("geiger_counter", new ItemWithTooltip(new Item.Properties().stacksTo(1), "geiger_counter", 30));

    private static Item registerItem(String name, Item item){
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AguilexStupidities.MOD_ID, name), item);
    }

    //Function called from Aguilexht.java to load items
    public static void registerModItems(){
        AguilexStupidities.LOGGER.info("Registering mod items for: " + AguilexStupidities.MOD_ID);
    }
}
