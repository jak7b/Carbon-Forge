package net.kuko.openthings.block;

import net.kuko.openthings.OpenThingsMod;
import net.kuko.openthings.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, OpenThingsMod.MOD_ID);
//
//
//    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
//            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
//                    .sound(SoundType.AMETHYST)
//                    .requiresCorrectToolForDrops()
//                    .strength(4.5f)
//            ));
//
//
//
//    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK = registerBlock("raw_sapphire_block",
//            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
//                    .sound(SoundType.AMETHYST)
//                    .requiresCorrectToolForDrops()
//                    .strength(5.5f)
//            ));
//
//






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;

    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus );
        OpenThingsMod.LOGGER.info("ModBlocks registering for " + OpenThingsMod.MOD_ID);
    }
}
