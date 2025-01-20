package net.mana7na.tutorialmod.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.mana7na.tutorialmod.TutorialMod;
import net.mana7na.tutorialmod.block.ModBlocks;
import net.mana7na.tutorialmod.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> PINK_GARNET_SHELTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE,
                ModBlocks.PINK_GARNET_DEEPSLATE_ORE);

        offerSmelting(exporter,PINK_GARNET_SHELTABLES, RecipeCategory.MISC,ModItems.PINK_GARNET,0.25f,200,"pink_garnet");
        offerBlasting(exporter,PINK_GARNET_SHELTABLES, RecipeCategory.MISC,ModItems.PINK_GARNET,0.25f,100,"pink_garnet");

//        CompactingRecipes ９このアイテムをひとつのブロックにまとめる（ダイヤ９個でダイヤブロック）
        offerReversibleCompactingRecipes(exporter,RecipeCategory.BUILDING_BLOCKS,ModItems.PINK_GARNET,RecipeCategory.DECORATIONS,ModBlocks.PINK_GARNET_BLOCK);

//        ShapedRecipeJsonBuilder 配置に指定があるアイテムの生成に使用
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModBlocks.RAW_PINK_GARNET_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_PINK_GARNET)
                .criterion(hasItem(ModItems.RAW_PINK_GARNET), conditionsFromItem(ModItems.RAW_PINK_GARNET))
                .offerTo(exporter);

        //        ShapelessRecipeJsonBuilder 配置に指定がないアイテムの生成に使用
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.RAW_PINK_GARNET,9)
                .input(ModBlocks.RAW_PINK_GARNET_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK),conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .offerTo(exporter);

//                レシピの名前を宣言できる
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.RAW_PINK_GARNET,32)
                .input(ModBlocks.MAGIC_BLOCK)
                .criterion(hasItem(ModBlocks.MAGIC_BLOCK),conditionsFromItem(ModBlocks.MAGIC_BLOCK))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID,"raw_pink_garnet_from_magic_block"));


    }
}
