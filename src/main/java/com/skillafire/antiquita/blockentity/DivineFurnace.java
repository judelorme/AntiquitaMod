package com.skillafire.antiquita.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DivineFurnace extends BaseEntityBlock {

	public static final Properties props = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN)
			.strength(1.0f)
			.sound(SoundType.STONE)
			.requiresCorrectToolForDrops()
			.emissiveRendering((state, getter, pos) -> true);

	public DivineFurnace() {
		super(props);
	}

	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 8, 16);
	
	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos pos,	CollisionContext collisionContext) {
		return SHAPE;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DivineFurnaceBlockEntity(pos, state);
	}
	
	
	@Override
	public RenderShape getRenderShape(BlockState p_49232_) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
}
