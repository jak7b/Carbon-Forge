package net.kuko.carbon.init;

import net.kuko.carbon.CarbonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class CreativeModTabsInit {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CarbonMod.MOD_ID);


//
//
//    public static final RegistryObject<CreativeModeTab> TUTORIAL_SAPPHIRE_TAB = CREATIVE_MODE_TABS.register("sapphire_tab",
//            () -> CreativeModeTab.builder()
//                    .icon(() -> new ItemStack(ItemInit.SAPPHIRE.get()))
//                    .title(Component.translatable("creativemodtab.sapphire_tab"))
//                    //.withTabsBefore()
//                    .displayItems((pParameters, pOutput) ->  {
//                        pOutput.accept(ItemInit.RAW_SAPPHIRE.get());
//                        pOutput.accept(ItemInit.SAPPHIRE.get());
//
//                        pOutput.accept(BlockInit.SAPPHIRE_BLOCK.get());
//                        pOutput.accept(BlockInit.RAW_SAPPHIRE_BLOCK.get());
//                    })
//                    .build());


    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
        CarbonMod.LOGGER.info("ModCreativeTabs registering for " + CarbonMod.MOD_ID);
    }
}
