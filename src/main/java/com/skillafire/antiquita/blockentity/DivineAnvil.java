package com.skillafire.antiquita.blockentity;

import com.skillafire.antiquita.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class DivineAnvil extends BaseEntityBlock {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public static final Properties props = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN)
			.strength(1.0f)
			.sound(SoundType.METAL)
			.noOcclusion();

	public DivineAnvil() {
		super(props);
	}

	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 14, 16);
	
	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos pos,	CollisionContext collisionContext) {
		return SHAPE;
	}

	// Facing
	
		@Override
		public BlockState getStateForPlacement(BlockPlaceContext context) {
			return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
		}
		
		@Override
		public BlockState rotate(BlockState blockState, Rotation rotation) {
			return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public BlockState mirror(BlockState blockState, Mirror mirror) {
			return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
		}
		
		@Override
		protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
			builder.add(FACING);
		}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DivineAnvilBlockEntity(pos, state);
	}
	
	
	@Override
	public RenderShape getRenderShape(BlockState p_49232_) {
		return RenderShape.MODEL;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState newState,	boolean isMoving) {
		if (blockState.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof DivineAnvilBlockEntity) {
				((DivineAnvilBlockEntity) blockEntity).drops();
			}
		}
				
		super.onRemove(blockState, level, pos, newState, isMoving);
	}
	
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
			InteractionHand hand, BlockHitResult result) {
		if (!level.isClientSide) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof DivineAnvilBlockEntity) {
				NetworkHooks.openGui(((ServerPlayer) player), (DivineAnvilBlockEntity) blockEntity, pos);
			} else {
				throw new IllegalStateException("Our container provider is missing!");
			}
		}
		
		return InteractionResult.sidedSuccess(level.isClientSide);
	}
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> blockEntityType) {
		return createTickerHelper(blockEntityType, BlockEntityInit.DIVINE_ANVIL_BLOCK_ENTITY.get(), DivineAnvilBlockEntity::tick);
	}
}
