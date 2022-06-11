package com.skillafire.antiquita.block;

import com.skillafire.antiquita.init.ItemInit;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SorghumPlantBlock extends CropBlock {
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);

	public static final Properties props = BlockBehaviour.Properties.copy(Blocks.WHEAT)
												.noOcclusion();
	
	public SorghumPlantBlock() {
		super(props);
	}

	@Override
	public int getMaxAge() {
		return 4;
	}
	
	@Override
	protected ItemLike getBaseSeedId() {
		return ItemInit.SORGHUM_SEEDS.get();
	}
}
