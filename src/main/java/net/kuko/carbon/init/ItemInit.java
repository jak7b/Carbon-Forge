package net.kuko.carbon.init;

import net.kuko.carbon.CarbonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, CarbonMod.MOD_ID);
//
//
//    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
//            () -> new Item(new Item.Properties()));
//
//    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
//            () -> new Item(new Item.Properties()));




    public static void register(IEventBus bus) {
        ITEMS.register(bus );
        CarbonMod.LOGGER.info("ItemInit registering for " + CarbonMod.MOD_ID);
    }
}
