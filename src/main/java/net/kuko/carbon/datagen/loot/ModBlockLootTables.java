package net.kuko.carbon.datagen.loot;

import net.kuko.carbon.init.BlockInit;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
//        this.dropSelf(BlockInit.SAPPHIRE_BLOCK.get()); // <- Drops itself
//        this.dropSelf(BlockInit.RAW_SAPPHIRE_BLOCK.get()); // <- Drops itself

//        this.add(BlockInit.SAPPHIRE_ORE.get(), // <- Creates copperLikeDrops. It drops Raw Sapphire.
//                block -> createCopperLikeOreDrops(BlockInit.SAPPHIRE_ORE.get(), ItemInit.RAW_SAPPHIRE.get(), UniformGenerator.between(2f,6f)));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }


    protected LootTable.Builder createCopperLikeOreDrops(Block block, Item item, UniformGenerator uniformGenerator) {
        return createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(uniformGenerator))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
}
