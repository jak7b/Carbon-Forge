package net.kuko.openthings.item;

import net.kuko.openthings.OpenThingsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, OpenThingsMod.MOD_ID);
//
//
//    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
//            () -> new Item(new Item.Properties()));
//
//    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
//            () -> new Item(new Item.Properties()));




    public static void register(IEventBus bus) {
        ITEMS.register(bus );
        OpenThingsMod.LOGGER.info("ModItems registering for " + OpenThingsMod.MOD_ID);
    }
}
