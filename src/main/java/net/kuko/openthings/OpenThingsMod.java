package net.kuko.openthings;

import com.mojang.logging.LogUtils;
import net.kuko.openthings.block.ModBlocks;
import net.kuko.openthings.item.ModCreativeModeTabs;
import net.kuko.openthings.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static net.kuko.openthings.OpenThingsMod.MOD_ID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
public class OpenThingsMod {
    public static final String MOD_ID = "openthings";
    public static final Logger LOGGER = LogUtils.getLogger();

    public OpenThingsMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        System.out.println("Common setup logic.");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
//            event.accept(ModItems.SAPPHIRE);
//            event.accept(ModItems.RAW_SAPPHIRE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        System.out.println("Server setup logic.");
    }
}
