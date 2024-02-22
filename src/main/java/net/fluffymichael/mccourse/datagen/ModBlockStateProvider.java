package net.fluffymichael.mccourse.datagen;

import net.fluffymichael.mccourse.McCourse;
import net.fluffymichael.mccourse.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, McCourse.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.RAW_ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);

        blockWithItem(ModBlocks.SOUND_BLOCK);

        stairsBlock((StairBlock) ModBlocks.ALEXANDRITE_STAIRS.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        blockItem(ModBlocks.ALEXANDRITE_STAIRS);
        slabBlock(((SlabBlock) ModBlocks.ALEXANDRITE_SLAB.get()), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        blockItem(ModBlocks.ALEXANDRITE_SLAB);

    }

    private void blockItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("mccourse:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
