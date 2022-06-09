package com.skillafire.antiquita.blockentity.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class ItemStackHandlerWrapper implements IItemHandler, IItemHandlerModifiable, INBTSerializable<CompoundTag> {
	public NonNullList<ItemStack> stacks;
	
	public Map<Integer, Integer> stackTypes = new HashMap<Integer, Integer>();
	
	private LazyOptional<IItemHandler> lazyPlayerItemHandler = LazyOptional.empty();
	private final PlayerItemStackHandler itemPlayerHandler;
	
	private LazyOptional<IItemHandler> lazyMachineItemHandler = LazyOptional.empty();
	private final MachineItemStackHandler itemMachineHandler;
	
	public ItemStackHandlerWrapper(BlockEntity entity, int slots, final Map<Integer, Integer> stackTypes) {
		this.stacks = NonNullList.withSize(slots, ItemStack.EMPTY);
		this.stackTypes = stackTypes;
		this.itemMachineHandler = new MachineItemStackHandler(stacks, stackTypes) {
			@Override
			protected void onContentsChanged(int slot) {
				entity.setChanged();
			};
		};
		this.itemPlayerHandler = new PlayerItemStackHandler(stacks, stackTypes) {
			@Override
			protected void onContentsChanged(int slot) {
				entity.setChanged();
			};
		};
	}
	
	public <T> LazyOptional<T> getCapability(boolean fromPlayer) {
		if (fromPlayer)
			return lazyPlayerItemHandler.cast();
		else 
			return lazyMachineItemHandler.cast();
	}
	
	public void onLoad() {
		this.lazyPlayerItemHandler = LazyOptional.of(() -> itemPlayerHandler);
		this.lazyMachineItemHandler = LazyOptional.of(() -> itemMachineHandler);
	}
	
	public void invalidateCaps() {
		this.lazyPlayerItemHandler.invalidate();
		this.lazyMachineItemHandler.invalidate();
	}

	@Override
    public CompoundTag serializeNBT()
    {
        System.out.println("Serializing NBT...");
        
        ListTag nbtTagList = new ListTag();
        for (int i = 0; i < stacks.size(); i++)
        {
            if (!stacks.get(i).isEmpty())
            {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                stacks.get(i).save(itemTag);
                nbtTagList.add(itemTag);
            }
        }
        CompoundTag nbt = new CompoundTag();
        nbt.put("Items", nbtTagList);
        nbt.putInt("Size", stacks.size());
        
        System.out.println("NBT output : " + nbt.getAsString());
        
        System.out.println("Serializing NBT done !");
        
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt)
    {
    	System.out.println("Deserializing NBT...");
        setSize(nbt.contains("Size", Tag.TAG_INT) ? nbt.getInt("Size") : stacks.size());
        ListTag tagList = nbt.getList("Items", Tag.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++)
        {
            CompoundTag itemTags = tagList.getCompound(i);
            int slot = itemTags.getInt("Slot");

            if (slot >= 0 && slot < stacks.size())
            {
                stacks.set(slot, ItemStack.of(itemTags));
                System.out.println("Inserting " + stacks.get(slot).getDisplayName().getString() + " in slot " + slot);
            }
        }
        System.out.println("Deserializing NBT done !");
        
        onLoad();
    }
    
    

    public void setSize(int size)
    {
        stacks = NonNullList.withSize(size, ItemStack.EMPTY);
        itemPlayerHandler.setStack(stacks);
        itemMachineHandler.setStack(stacks);
    }

	@Override
	public int getSlots() {
		return this.stacks.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.stacks.get(slot);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return itemPlayerHandler.insertItem(slot, stack, simulate);
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return itemPlayerHandler.extractItem(slot, amount, simulate);
	}

	@Override
	public int getSlotLimit(int slot) {
		return 64;
	}

	@Override
	public boolean isItemValid(int slot, ItemStack stack) {
		return itemPlayerHandler.isItemValid(slot, stack);
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		itemPlayerHandler.setStackInSlot(slot, stack);
	}
}
