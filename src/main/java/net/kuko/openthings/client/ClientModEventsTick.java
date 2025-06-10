package net.kuko.openthings.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag; // Import for Tag
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.kuko.openthings.OpenThingsMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientModEventsTick {
    private static boolean wasCrouching = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        nbtReaderFromHand(event);
    }

    public static void nbtReaderFromHand(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;

        if (player == null) return;
        boolean isCrouching = player.isCrouching();

        // Detect crouch just starting this tick
        if (isCrouching && !wasCrouching) {
            ItemStack heldItemStack = player.getMainHandItem();
            if (!heldItemStack.isEmpty()) {
                CompoundTag itemFullNBT = heldItemStack.serializeNBT(); // Get the full serialized NBT
                player.sendSystemMessage(Component.literal("--- Full Item NBT Structure ---"));
                printNBT(player, itemFullNBT, 0); // Start recursive printing from depth 0
                player.sendSystemMessage(Component.literal("-------------------------------"));

            } else {
                return;
            }
        }

        // Update crouch state
        wasCrouching = isCrouching;
    }

    // Recursive helper method to print NBT
    private static void printNBT(LocalPlayer player, CompoundTag nbt, int depth) {
        String indent = "  ".repeat(depth); // For visual indentation in chat

        if (nbt.isEmpty()) {
            player.sendSystemMessage(Component.literal(indent + "{ } (Empty Compound)"));
            return;
        }

        nbt.getAllKeys().forEach(key -> {
            Tag value = nbt.get(key); // Get the raw NBT tag
            String typeName = value.getType().getName(); // Get the type name (e.g., "TAG_String", "TAG_Compound")

            if (value instanceof CompoundTag compoundValue) {
                player.sendSystemMessage(Component.literal(indent + key + ": { " + typeName + " }"));
                printNBT(player, compoundValue, depth + 1); // Recurse into nested CompoundTag
            } else if (value instanceof ListTag listValue) {
                player.sendSystemMessage(Component.literal(indent + key + ": [ " + typeName + " ]"));
                printListTag(player, listValue, depth + 1); // Handle ListTag
            } else {
                // For other types (String, Int, Byte, etc.), just print key and value
                player.sendSystemMessage(Component.literal(indent + key + ": " + value.getAsString() + " (" + typeName + ")"));
            }
        });
    }

    // Helper method to print ListTag contents
    private static void printListTag(LocalPlayer player, ListTag list, int depth) {
        String indent = "  ".repeat(depth);

        if (list.isEmpty()) {
            player.sendSystemMessage(Component.literal(indent + "[ ] (Empty List)"));
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Tag element = list.get(i);
            String typeName = element.getType().getName();

            if (element instanceof CompoundTag compoundElement) {
                player.sendSystemMessage(Component.literal(indent + "[" + i + "]: { " + typeName + " }"));
                printNBT(player, compoundElement, depth + 1); // Recurse for compound elements in list
            } else if (element instanceof ListTag listElement) { // Nested list
                player.sendSystemMessage(Component.literal(indent + "[" + i + "]: [ " + typeName + " ]"));
                printListTag(player, listElement, depth + 1);
            }
            else {
                player.sendSystemMessage(Component.literal(indent + "[" + i + "]: " + element.getAsString() + " (" + typeName + ")"));
            }
        }
    }
}