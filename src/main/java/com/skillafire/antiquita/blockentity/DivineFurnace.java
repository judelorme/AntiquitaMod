package com.skillafire.antiquita.blockentity;

import com.skillafire.antiquita.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class DivineFurnace extends BaseEntityBlock {
	
	public static final Properties props = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN)
			.strength(1.0f)
			.sound(SoundType.STONE)
			.noOcclusion()
			.requiresCorrectToolForDrops();

	public DivineFurnace() {
		super(props);
	}

	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 14, 16);
	
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
	
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
			InteractionHand hand, BlockHitResult result) {
		if (!level.isClientSide) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof DivineFurnaceBlockEntity) {
				NetworkHooks.openGui(((ServerPlayer) player), (DivineFurnaceBlockEntity) blockEntity, pos);
			} else {
				throw new IllegalStateException("Our container provider is missing!");
			}
		}
		
		return InteractionResult.sidedSuccess(level.isClientSide);
	}
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> blockEntityType) {
		return createTickerHelper(blockEntityType, BlockEntityInit.DIVINE_FURNACE_BLOCK_ENTITY.get(), DivineFurnaceBlockEntity::tick);
	}
}
