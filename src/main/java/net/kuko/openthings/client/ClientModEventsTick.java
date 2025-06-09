package net.kuko.openthings.client;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.kuko.openthings.OpenThingsMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientModEventsTick {
    static Boolean hasPrinted = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        sneak(event);
    }
    public static void sneak(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                if (minecraft.player == null) return;

                ItemStack mainHandStack = minecraft.player.getMainHandItem();

                if (minecraft.player.isCrouching() && mainHandStack.is(Items.NETHERITE_SWORD)) {
                    if (!hasPrinted) {
                        CompoundTag compoundTag = mainHandStack.serializeNBT();
                        minecraft.player.sendSystemMessage(
                                Component.literal("NBT: " + compoundTag.toString())
                        );
                        hasPrinted = true;
                    }
                } else {
                    hasPrinted = false; // Reset when not sneaking or not holding the item
                }
            }
        }
    }
}

