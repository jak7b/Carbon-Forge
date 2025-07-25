package net.kuko.carbon.datagen;

import net.kuko.carbon.CarbonMod;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CarbonMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        blockWithItem(BlockInit.SAPPHIRE_BLOCK);
//        blockWithItem(BlockInit.RAW_SAPPHIRE_BLOCK);
    }

    /**
     * Helper method to register both a block's state and its item model.
     * @param blockRegistryObject The RegistryObject of the block to register.
     */
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
