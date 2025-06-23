package net.kuko.carbon.datagen;

import net.kuko.carbon.CarbonMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
//import org.embeddedt.embeddium.compat.EmbeddiumCompat;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CarbonMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        simpleItem(ItemInit.SAPPHIRE); /// <- Creates normale json for item model, nothing complex, I fink.
//        simpleItem(ItemInit.RAW_SAPPHIRE);
//
//        simpleItem(ItemInit.METAL_DETECTOR);
//        simpleItem(ItemInit.PINE_CONE);
//        simpleItem(ItemInit.STRAWBERRY);


    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) { // <- Adds a simple item model into Assets/Namespace/Models/Item
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.tryParse("item/generated")).texture("layer0", // <- originally: new ResourceLocation(...) but it got flagged for being outdated.
                ResourceLocation.tryBuild(CarbonMod.MOD_ID, "item/" + item.getId().getPath())); // <- originally: new ResourceLocation(...) but it got flagged for being outdated.
    }
}
