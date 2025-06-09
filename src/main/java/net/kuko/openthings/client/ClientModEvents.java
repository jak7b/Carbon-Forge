package net.kuko.openthings.client;


import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.kuko.openthings.OpenThingsMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        makeColoredItemAndAddToItAnTag(event, Items.NETHERITE_SWORD, 0xFF0000);
    }

    public static void makeColoredItemAndAddToItAnTag(FMLClientSetupEvent event, Item item, int color) {
        event.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            ItemColors itemColors = mc.getItemColors();

            genTag(item).putInt("color", color);

            itemColors.register((stack, tintIndex) -> tintIndex == 0 ? color : -1, item);
        });
    }

    private static CompoundTag genTag(Item item) {
        ItemStack stack = new ItemStack(item);

        // Get or create the tag compound
        return stack.getOrCreateTag();

//        // Put some data, e.g., an integer under key "color"
//        tag.putInt("color", 0x00000); // green color

        // Now the stack has the tag with your custom data

    }

} // This is own ideptended class from main one
