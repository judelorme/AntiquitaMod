package com.skillafire.antiquita.screen;

import com.skillafire.antiquita.blockentity.DivineAnvilBlockEntity;
import com.skillafire.antiquita.init.BlockInit;
import com.skillafire.antiquita.init.MenuTypesInit;
import com.skillafire.antiquita.screen.slot.ModResultSlot;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DivineAnvilMenu extends AbstractContainerMenu {
	public static final int TOTAL_PROGRESS_PIXELS = 22;

	private final DivineAnvilBlockEntity blockEntity;
	private final Level level;
	private final ContainerData data;

	public DivineAnvilMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
		this(containerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()),
				new SimpleContainerData(DivineAnvilBlockEntity.DATA_AMOUNT));
	}

	public DivineAnvilMenu(int containerId, Inventory inv, BlockEntity entity, ContainerData data) {
		super(MenuTypesInit.DIVINE_ANVIL_MENU.get(), containerId);
		checkContainerSize(inv, DivineAnvilBlockEntity.SLOT_NUMBER);
		blockEntity = ((DivineAnvilBlockEntity) entity);
		this.level = inv.player.level;
		this.data = data;

		addPlayerInventory(inv);
		addPlayerHotbar(inv);

		this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
			this.addSlot(new SlotItemHandler(handler, 0, 30, 17));
			this.addSlot(new SlotItemHandler(handler, 1, 48, 17));
			this.addSlot(new SlotItemHandler(handler, 2, 66, 17));
			this.addSlot(new SlotItemHandler(handler, 3, 30, 35));
			this.addSlot(new SlotItemHandler(handler, 4, 48, 35));
			this.addSlot(new SlotItemHandler(handler, 5, 66, 35));
			this.addSlot(new SlotItemHandler(handler, 6, 30, 53));
			this.addSlot(new SlotItemHandler(handler, 7, 48, 53));
			this.addSlot(new SlotItemHandler(handler, 8, 66, 53));
			this.addSlot(new ModResultSlot(handler, DivineAnvilBlockEntity.OUTPUT_SLOT_NUMBER, 124, 35));
		});

		addDataSlots(data);
	}

	public boolean isCrafting() {
		return data.get(0) > 0;
	}

	public int getScaledProgress() {
		int progress = this.data.get(0);
		int maxProgress = this.data.get(1);

		return progress != 0 && maxProgress != 0 ? progress * TOTAL_PROGRESS_PIXELS / maxProgress : 0;
	}

	// CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
	// must assign a slot number to each of the slots used by the GUI.
	// For this container, we can see both the tile inventory's slots as well as the
	// player inventory slots and the hotbar.
	// Each time we add a Slot to the container, it automatically increases the
	// slotIndex, which means
	// 0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 -
	// 8)
	// 9 - 35 = player inventory slots (which map to the InventoryPlayer slot
	// numbers 9 - 35)
	// 36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 -
	// 8)
	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
	private static final int VANILLA_FIRST_SLOT_INDEX = 0;
	private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

	// THIS YOU HAVE TO DEFINE!
	private static final int TE_INVENTORY_SLOT_COUNT = DivineAnvilBlockEntity.SLOT_NUMBER; // must be the number of
																								// slots you have!

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		Slot sourceSlot = slots.get(index);
		if (sourceSlot == null || !sourceSlot.hasItem())
			return ItemStack.EMPTY; // EMPTY_ITEM
		ItemStack sourceStack = sourceSlot.getItem();
		ItemStack copyOfSourceStack = sourceStack.copy();

		// Check if the slot clicked is one of the vanilla container slots
		if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
			// This is a vanilla container slot so merge the stack into the tile inventory
			if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX,
					TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
				return ItemStack.EMPTY; // EMPTY_ITEM
			}
		} else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
			// This is a TE slot so merge the stack into the players inventory
			if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT,
					false)) {
				return ItemStack.EMPTY;
			}
		} else {
			System.out.println("Invalid slotIndex:" + index);
			return ItemStack.EMPTY;
		}
		// If stack size == 0 (the entire stack was moved) set slot contents to null
		if (sourceStack.getCount() == 0) {
			sourceSlot.set(ItemStack.EMPTY);
		} else {
			sourceSlot.setChanged();
		}
		sourceSlot.onTake(playerIn, sourceStack);
		return copyOfSourceStack;
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
				BlockInit.DIVINE_ANVIL.get());
	}

	private void addPlayerInventory(Inventory playerInventory) {
		for (int line = 0; line < 3; ++line) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(
						new Slot(playerInventory, column + (line + 1) * 9 /* + 9 */, 8 + column * 18, 84 + line * 18));
			}
		}
	}

	private void addPlayerHotbar(Inventory playerInventory) {
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
		}
	}
}