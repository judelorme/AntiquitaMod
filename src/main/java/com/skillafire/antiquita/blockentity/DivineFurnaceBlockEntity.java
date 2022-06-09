package com.skillafire.antiquita.blockentity;

import java.util.HashMap;
import java.util.Optional;

import javax.annotation.Nonnull;

import com.skillafire.antiquita.blockentity.common.ItemSlotTypesEnum;
import com.skillafire.antiquita.blockentity.common.ItemStackHandlerWrapper;
import com.skillafire.antiquita.init.BlockEntityInit;
import com.skillafire.antiquita.recipe.DivineFurnaceRecipe;
import com.skillafire.antiquita.screen.DivineFurnaceMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DivineFurnaceBlockEntity extends BlockEntity implements IAnimatable, MenuProvider {
	public static final int SLOT_NUMBER = 3;
	public static final int OUTPUT_SLOT_NUMBER = SLOT_NUMBER - 1;
	public static final int DATA_AMOUNT = 2;
	public static final String TAG_PROGRESS = "divine_furnace.progress";
	public static final String TAG_MAX_PROGRESS = "divine_furnace.max_progress";
	public static final String TAG_INVENTORY = "divine_furnace.inventory";

	protected final ContainerData data;
	private int progress = 0;
	private int maxProgress = 72;

	public final ItemStackHandlerWrapper capabilityWrapper = new ItemStackHandlerWrapper(this, SLOT_NUMBER,
			new HashMap<Integer, Integer>() {
				private static final long serialVersionUID = 1L;

				{
					put(0, ItemSlotTypesEnum.INPUT | ItemSlotTypesEnum.ITEM);
					put(1, ItemSlotTypesEnum.INPUT | ItemSlotTypesEnum.ITEM);
					put(2, ItemSlotTypesEnum.OUTPUT | ItemSlotTypesEnum.ITEM);
				}
			});

	private AnimationFactory factory = new AnimationFactory(this);
	private int currentState = 0;
	private Boolean hasFilled = false;

	public DivineFurnaceBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.DIVINE_FURNACE_BLOCK_ENTITY.get(), pos, state);

		this.data = new ContainerData() {

			@Override
			public void set(int index, int value) {
				switch (index) {
				case 0:
					DivineFurnaceBlockEntity.this.progress = value;
					break;
				case 1:
					DivineFurnaceBlockEntity.this.maxProgress = value;
					break;
				}
			}

			@Override
			public int getCount() {
				return DATA_AMOUNT;
			}

			@Override
			public int get(int index) {
				switch (index) {
				case 0:
					return DivineFurnaceBlockEntity.this.progress;
				case 1:
					return DivineFurnaceBlockEntity.this.maxProgress;
				default:
					return 0;
				}
			}
		};
	}

	public static void tick(Level level, BlockPos pos, BlockState blockState, DivineFurnaceBlockEntity blockEntity) {
		ItemStack outputItem = hasRecipe(blockEntity);

		if (outputItem != null) {
			blockEntity.progress++;
			setChanged(level, pos, blockState);

			if (blockEntity.progress > blockEntity.maxProgress) {
				if (!level.isClientSide) {
					craftItem(blockEntity, outputItem);
				}
			}
		} else {
			blockEntity.resetProgress();
			setChanged(level, pos, blockState);
		}

	}
	
	private static ItemStack hasRecipe(DivineFurnaceBlockEntity blockEntity) {
		Level level = blockEntity.level;
		SimpleContainer inventory = new SimpleContainer(blockEntity.capabilityWrapper.getSlots());

		inventory.setItem(0, blockEntity.capabilityWrapper.getStackInSlot(0));
		inventory.setItem(1, blockEntity.capabilityWrapper.getStackInSlot(1));
		inventory.setItem(OUTPUT_SLOT_NUMBER, blockEntity.capabilityWrapper.getStackInSlot(OUTPUT_SLOT_NUMBER));

		Optional<DivineFurnaceRecipe> match = level.getRecipeManager()
				.getRecipeFor(DivineFurnaceRecipe.DivineFurnaceRecipeType.INSTANCE, inventory, level);

		if (match.isPresent()
				&& canInsertAmountIntoOutputSlot(inventory, match.get().getResultItem())
				&& canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())) {
			return match.get().getResultItem();
		}

		return null;
	}
	
	private static void craftItem(DivineFurnaceBlockEntity blockEntity, ItemStack outputItem) {
		blockEntity.capabilityWrapper.extractItem(0, 1, false);
		blockEntity.capabilityWrapper.extractItem(1, 1, false);
		blockEntity.capabilityWrapper.setStackInSlot(OUTPUT_SLOT_NUMBER, new ItemStack(outputItem.getItem(),
				blockEntity.capabilityWrapper.getStackInSlot(OUTPUT_SLOT_NUMBER).getCount() + outputItem.getCount()));
		blockEntity.resetProgress();
	}
	
	private void resetProgress() {
		this.progress = 0;
	}
	
	public void drops() {
		SimpleContainer inventory = new SimpleContainer(capabilityWrapper.getSlots());
		for (int i = 0; i < capabilityWrapper.getSlots(); i++) {
			inventory.setItem(i, capabilityWrapper.getStackInSlot(i));
		}

		Containers.dropContents(level, worldPosition, inventory);
	}
	
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::predicate));
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.currentState == 0 && event.getController().getCurrentAnimation() != null) {
		}

		if (this.currentState == 1 && !this.hasFilled) {
			event.getController()
					.setAnimation(new AnimationBuilder().addAnimation("animation.divinefurnace.fill", false));
			this.hasFilled = true;
			return PlayState.CONTINUE;
		}

		if (this.currentState == 1 && this.hasFilled) {
			if (event.getController().getAnimationState() == AnimationState.Stopped)
				event.getController()
						.setAnimation(new AnimationBuilder().addAnimation("animation.divinefurnace.crafting", true));
		}

		if (this.currentState == 2) {
			event.getController()
					.setAnimation(new AnimationBuilder().addAnimation("animation.divinefurnace.empty", false));
			this.currentState = 0;
			this.hasFilled = false;
			return PlayState.CONTINUE;
		}

		return PlayState.CONTINUE;
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	public void PlayAnimation() {
		if (this.currentState == 0)
			this.currentState = 1;
		else if (this.currentState == 1)
			this.currentState = 2;
		else if (this.currentState == 2)
			this.currentState = 0;
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
		tag.putInt(TAG_MAX_PROGRESS, maxProgress);
		tag.put(TAG_INVENTORY, capabilityWrapper.serializeNBT());

		super.saveAdditional(tag);
	}
	
	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = new CompoundTag();
		tag.putInt(TAG_PROGRESS, progress);
		tag.putInt(TAG_MAX_PROGRESS, maxProgress);
		tag.put(TAG_INVENTORY, capabilityWrapper.serializeNBT());

		return tag;
	}
	
	@Override
	public void load(CompoundTag tag) {
		super.load(tag);

		capabilityWrapper.deserializeNBT(tag.getCompound(TAG_INVENTORY));
		progress = tag.getInt(TAG_PROGRESS);
		maxProgress = tag.getInt(TAG_MAX_PROGRESS);
	}
	
	@Override
	public void handleUpdateTag(CompoundTag tag) {
		super.handleUpdateTag(tag);
		load(tag);
	}
	
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
	
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new DivineFurnaceMenu(containerId, inventory, this, this.data);
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent(new TranslatableComponent("block.antiquita.divine_furnace").getString());
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
		return inventory.getItem(OUTPUT_SLOT_NUMBER).getCount() + itemStack.getCount() <= inventory
				.getItem(OUTPUT_SLOT_NUMBER).getMaxStackSize();
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
		return inventory.getItem(OUTPUT_SLOT_NUMBER).getItem() == output.getItem()
				|| inventory.getItem(OUTPUT_SLOT_NUMBER).isEmpty();
	}
}
