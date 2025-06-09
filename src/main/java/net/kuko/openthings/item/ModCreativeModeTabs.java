package net.kuko.openthings.item;

import net.kuko.openthings.OpenThingsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OpenThingsMod.MOD_ID);


//
//
//    public static final RegistryObject<CreativeModeTab> TUTORIAL_SAPPHIRE_TAB = CREATIVE_MODE_TABS.register("sapphire_tab",
//            () -> CreativeModeTab.builder()
//                    .icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
//                    .title(Component.translatable("creativemodtab.sapphire_tab"))
//                    //.withTabsBefore()
//                    .displayItems((pParameters, pOutput) ->  {
//                        pOutput.accept(ModItems.RAW_SAPPHIRE.get());
//                        pOutput.accept(ModItems.SAPPHIRE.get());
//
//                        pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
//                        pOutput.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
//                    })
//                    .build());


    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
        OpenThingsMod.LOGGER.info("ModCreativeTabs registering for " + OpenThingsMod.MOD_ID);
    }
}
