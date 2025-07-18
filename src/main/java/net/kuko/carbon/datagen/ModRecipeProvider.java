package net.kuko.carbon.datagen;

import net.kuko.carbon.CarbonMod;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

//    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(
//            ItemInit.RAW_SAPPHIRE.get()
//    );

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
//        oreSmelting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ItemInit.SAPPHIRE.get(), 0.25f, 200, "sapphire");
//        oreBlasting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ItemInit.SAPPHIRE.get(), 0.25f, 100, "sapphire");
//
//        createTwoWayRecipes(pWriter, ItemInit.SAPPHIRE.get(), BlockInit.SAPPHIRE_BLOCK.get(), RecipeCategory.BUILDING_BLOCKS, CarbonMod.MOD_ID);
//        createTwoWayRecipes(pWriter, ItemInit.RAW_SAPPHIRE.get(), BlockInit.RAW_SAPPHIRE_BLOCK.get(), RecipeCategory.BUILDING_BLOCKS, CarbonMod.MOD_ID);
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            String recipeName = getMyItemName(pResult) + pRecipeName + "_" + getMyItemName(itemlike);
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, ResourceLocation.tryBuild(CarbonMod.MOD_ID, recipeName));
        }
    }

    public static void createTwoWayRecipes(Consumer<FinishedRecipe> consumer, Item item, Block block, RecipeCategory blockCategory, String modId) {
        ShapedRecipeBuilder.shaped(blockCategory, block)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', item)
                .unlockedBy(getHasName(item), has(item))
                .save(consumer, ResourceLocation.tryBuild(modId, getMyItemName(block) + "_from_item"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item, 9)
                .requires(block)
                .unlockedBy(getHasName(block), has(block))
                .save(consumer, ResourceLocation.tryBuild(modId, "deconstruct_" + getMyItemName(block)));
    }

    // Helper methods.  Renamed getItemName to getMyItemName
    private static String getMyItemName(ItemLike itemLike) {
        return ForgeRegistries.ITEMS.getKey(itemLike.asItem()).getPath();
    }

    private static String getBlockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
