package com.skillafire.antiquita.blockentity;

import java.util.HashMap;
import java.util.Optional;

import javax.annotation.Nonnull;

import com.skillafire.antiquita.blockentity.common.ItemSlotTypesEnum;
import com.skillafire.antiquita.blockentity.common.ItemStackHandlerWrapper;
import com.skillafire.antiquita.init.BlockEntityInit;
import com.skillafire.antiquita.init.ItemInit;
import com.skillafire.antiquita.recipe.DivineSmelteryRecipe;
import com.skillafire.antiquita.screen.DivineSmelteryMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

public class DivineSmelteryBlockEntity extends BlockEntity implements MenuProvider {
	
	public static final int SLOT_NUMBER = 4;
	public static final int OUTPUT_SLOT_NUMBER = SLOT_NUMBER - 1;
	public static final int DATA_AMOUNT = 2;
	public static final String TAG_PROGRESS = "divine_smeltery.progress";
	public static final String TAG_INVENTORY = "divine_smeltery.inventory";
	
	protected final ContainerData data;
	private int progress = 0;
	private int maxProgress = 72;
	
	public final ItemStackHandlerWrapper capabilityWrapper = new ItemStackHandlerWrapper(this, SLOT_NUMBER, new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;

	{
	    put(0, ItemSlotTypesEnum.INPUT | ItemSlotTypesEnum.ITEM);
	    put(1, ItemSlotTypesEnum.INPUT | ItemSlotTypesEnum.ITEM);
	    put(2, ItemSlotTypesEnum.INPUT | ItemSlotTypesEnum.ITEM | ItemSlotTypesEnum.UPGRADE);
	    put(3, ItemSlotTypesEnum.OUTPUT | ItemSlotTypesEnum.ITEM);
	}});
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public DivineSmelteryBlockEntity(BlockPos pos, BlockState blockState) {
		super(BlockEntityInit.DIVINE_SMELTERY_BLOCK_ENTITY.get(), pos, blockState);
		
		this.data = new ContainerData() {
			
			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0: DivineSmelteryBlockEntity.this.progress = value; break;
					case 1: DivineSmelteryBlockEntity.this.maxProgress = value; break;
				}
			}
			
			@Override
			public int getCount() {
				return DATA_AMOUNT;
			}
			
			@Override
			public int get(int index) {
				switch (index) {
					case 0: return DivineSmelteryBlockEntity.this.progress;
					case 1: return DivineSmelteryBlockEntity.this.maxProgress;
					default: return 0;
				}
			}
		};
	}


	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new DivineSmelteryMenu(containerId, inventory, this, this.data);
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent(new TranslatableComponent("block.antiquita.divine_smeltery").getString());
	}
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return capabilityWrapper.getCapability(side == null);
		}
		
		return super.getCapability(cap, side);
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
		capabilityWrapper.onLoad();
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		capabilityWrapper.invalidateCaps();
	}
	
	@Override
	protected void saveAdditional(CompoundTag tag) {
		tag.putInt(TAG_PROGRESS, progress);
		tag.put(TAG_INVENTORY, capabilityWrapper.serializeNBT());
		
		super.saveAdditional(tag);
	}
	
	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = new CompoundTag();
		tag.putInt(TAG_PROGRESS, progress);
		tag.put(TAG_INVENTORY, capabilityWrapper.serializeNBT());
		
		return tag;
	}
	
	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		
		capabilityWrapper.deserializeNBT(tag.getCompound(TAG_INVENTORY));
		progress = tag.getInt(TAG_PROGRESS);
	}
	
	public void drops() {
		SimpleContainer inventory = new SimpleContainer(capabilityWrapper.getSlots());
		for (int i = 0; i < capabilityWrapper.getSlots(); i++) {
			inventory.setItem(i, capabilityWrapper.getStackInSlot(i));
		}
		
		Containers.dropContents(level, worldPosition, inventory);
	}
	
	public static void tick(Level level, BlockPos pos, BlockState blockState, DivineSmelteryBlockEntity blockEntity) {
		if (!level.isClientSide) {
			ItemStack outputItem = hasRecipe(blockEntity);
			if (outputItem != null) {
				blockEntity.progress++;
				setChanged(level, pos, blockState);
				
				if (blockEntity.progress > blockEntity.maxProgress)
					craftItem(blockEntity, outputItem);
			} else {
				blockEntity.resetProgress();
				setChanged(level, pos, blockState);			
			}	
		}
	}
		
	private static void craftItem(DivineSmelteryBlockEntity blockEntity, ItemStack outputItem) {
		SimpleContainer inventory = new SimpleContainer(blockEntity.capabilityWrapper.getSlots());
		for (int i = 0; i < blockEntity.capabilityWrapper.getSlots(); i++) {
			inventory.setItem(i, blockEntity.capabilityWrapper.getStackInSlot(i));
		}
		
		blockEntity.capabilityWrapper.extractItem(0, 1, false);
		blockEntity.capabilityWrapper.extractItem(1, 1, false);
		
		blockEntity.capabilityWrapper.setStackInSlot(OUTPUT_SLOT_NUMBER, new ItemStack(outputItem.getItem(), blockEntity.capabilityWrapper.getStackInSlot(OUTPUT_SLOT_NUMBER).getCount() + outputItem.getCount() * DivineSmelteryBlockEntity.getBoostedOutputAmount(inventory.getItem(2))));
		blockEntity.resetProgress();
	}
	
	private static ItemStack hasRecipe(DivineSmelteryBlockEntity blockEntity) {
		Level level = blockEntity.level;
		
		SimpleContainer inventory = new SimpleContainer(blockEntity.capabilityWrapper.getSlots());
		for (int i = 0; i < blockEntity.capabilityWrapper.getSlots(); i++) {
			inventory.setItem(i, blockEntity.capabilityWrapper.getStackInSlot(i));
		}
		
		Optional<DivineSmelteryRecipe> match = level.getRecipeManager().getRecipeFor(DivineSmelteryRecipe.DivineSmelteryRecipeType.INSTANCE, inventory, level);
		
		if (match.isPresent() && canInsertAmountIntoOutputSlot(inventory, match.get().getResultItem()) && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())) {
			return match.get().getResultItem();
		}
				
		return null;
	}

	private void resetProgress() {
		this.progress = 0;
	}
	
	private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
		return inventory.getItem(OUTPUT_SLOT_NUMBER).getCount() + output.getCount() * getBoostedOutputAmount(inventory.getItem(2)) < inventory.getItem(OUTPUT_SLOT_NUMBER).getMaxStackSize();
	}
	
	private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
		return inventory.getItem(OUTPUT_SLOT_NUMBER).getItem() == output.getItem() || inventory.getItem(OUTPUT_SLOT_NUMBER).isEmpty();
	}
	
	private static int getBoostedOutputAmount(ItemStack itemStack) {
		if (itemStack.isEmpty())
			return 1;
		if (itemStack.is(ItemInit.DIVINE_BOOSTER_TIER_1.get())) 
			return 2;
		if (itemStack.is(ItemInit.DIVINE_BOOSTER_TIER_2.get())) 
			return 3;
		return 1;
	}
}
