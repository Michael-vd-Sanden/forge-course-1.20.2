package net.fluffymichael.mccourse.datagen;

import net.fluffymichael.mccourse.McCourse;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Block>> future,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, completableFuture, future, McCourse.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //Add Tags here
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
