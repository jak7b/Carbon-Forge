package net.kuko.openthings.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class NBTUtils {
    public static void setIntTagOnItem(ItemStack stack, String tagName, int value) {
        if (stack.isEmpty()) return; // Avoid empty stacks
        stack.getOrCreateTag().putInt(tagName, value);
    }

    public static void setStringTagOnItem(ItemStack stack, String tagName, String value) {
        if (stack.isEmpty()) return; // Avoid empty stacks
        stack.getOrCreateTag().putString(tagName, value);
    }
}