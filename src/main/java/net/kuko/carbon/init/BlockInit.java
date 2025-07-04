package net.kuko.carbon.init;

import net.kuko.carbon.CarbonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, CarbonMod.MOD_ID);
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
        return ItemInit.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus );
        CarbonMod.LOGGER.info("BlockInit registering for " + CarbonMod.MOD_ID);
    }
}
