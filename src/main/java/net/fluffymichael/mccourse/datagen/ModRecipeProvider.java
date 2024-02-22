package net.fluffymichael.mccourse.datagen;

import net.fluffymichael.mccourse.McCourse;
import net.fluffymichael.mccourse.block.ModBlocks;
import net.fluffymichael.mccourse.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ALEXANDRITE_SMELTABLES = List.of(ModItems.RAW_ALEXANDRITE.get(),
            ModBlocks.ALEXANDRITE_ORE.get(), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get());
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy("has_alexandrite", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE.get()))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 9)
                .requires(ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_alexandrite_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ALEXANDRITE_BLOCK.get()))
                .save(recipeOutput);
        //This one line is the same as the above. Check out the RecipeProvider class for more of these things
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE.get(), RecipeCategory.MISC, ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
                "mccourse:raw_alexandrite", null, "mccourse:raw_alexandrite_block", "alexandrite");

        oreSmelting(recipeOutput, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 200, "alexandrite");
        oreBlasting(recipeOutput, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 100, "alexandrite");

        slab(recipeOutput, RecipeCategory.MISC, ModBlocks.ALEXANDRITE_SLAB.get(), ModBlocks.ALEXANDRITE_BLOCK.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK.get())
                .pattern("A")
                .pattern("A")
                .define('A', ModBlocks.ALEXANDRITE_SLAB.get())
                .unlockedBy("has_alexandrite_slab", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ALEXANDRITE_SLAB.get()))
                .save(recipeOutput, new ResourceLocation("mccourse:alexandrite_block_from_slab"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_STAIRS.get(), 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_alexandrite_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ALEXANDRITE_BLOCK.get()))
                .save(recipeOutput);
    }
    //you need to add the methods and change some things in order to make them stop showing up in the minecraft folder
    private static void nineBlockStorageRecipes(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, @Nullable String pPackedGroup, String pUnpackedName, @Nullable String pUnpackedGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 9).requires(pPacked).group(pUnpackedGroup).unlockedBy(getHasName(pPacked),
                has(pPacked)).save(pRecipeOutput, new ResourceLocation(pUnpackedName));
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked).define('#',
                pUnpacked).pattern("###").pattern("###").pattern("###").group(pPackedGroup).unlockedBy(getHasName(pUnpacked),
                has(pUnpacked)).save(pRecipeOutput, new ResourceLocation(pPackedName));
    }
    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    private static void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<? extends AbstractCookingRecipe> pSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime,
                    pSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pRecipeOutput, McCourse.MODID + ":" +   //added
                    getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }

    }
}
