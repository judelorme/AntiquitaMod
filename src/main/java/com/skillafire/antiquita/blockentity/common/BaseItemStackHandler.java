package com.skillafire.antiquita.blockentity.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

public class BaseItemStackHandler implements IItemHandler, IItemHandlerModifiable {
	protected NonNullList<ItemStack> stacks;
	protected Map<Integer, Integer> stackTypes = new HashMap<Integer, Integer>();
	
	public BaseItemStackHandler(NonNullList<ItemStack> stacks, Map<Integer, Integer> stackTypes) {
		this.stacks = stacks;
        this.stackTypes = stackTypes;
	}
	
	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		validateSlotIndex(slot);
        this.stacks.set(slot, stack);
        onContentsChanged(slot);
	}
	
	@Override
	public int getSlots() {
		return stacks.size();
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		validateSlotIndex(slot);
        return this.stacks.get(slot);
	}
	
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (stack.isEmpty())
            return ItemStack.EMPTY;
            
        if (!isItemValid(slot, stack) || ItemSlotTypesEnum.checkMaskEquals(stackTypes.get(slot), ItemSlotTypesEnum.OUTPUT)) {
            return stack;
        }

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);
        
        int limit = getStackLimit(slot, stack);

        if (!existing.isEmpty())
        {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate)
        {
            if (existing.isEmpty())
            {
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else
            {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract)
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemStack.EMPTY);
                onContentsChanged(slot);
                return existing;
            }
            else
            {
                return existing.copy();
            }
        }
        else
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                onContentsChanged(slot);
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
	}
	
	@Override
	public int getSlotLimit(int slot) {
		return 64;
	}
	
	@Override
	public boolean isItemValid(int slot, ItemStack stack) {
		if (ItemSlotTypesEnum.checkMaskEquals(stackTypes.get(slot), ItemSlotTypesEnum.FUELEXCLUDED) && ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0) {
    		return false;	
    	}
    	
    	if (ItemSlotTypesEnum.checkMaskEquals(stackTypes.get(slot), ItemSlotTypesEnum.FUELONLY) && ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) <= 0) {
    		return false;	
    	}
    	
        return this.stacks.get(slot).getCount() < getSlotLimit(slot);
	}
	
	protected void validateSlotIndex(int slot)
    {
        if (slot < 0 || slot >= stacks.size())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + stacks.size() + ")");
    }
	
	protected void onContentsChanged(int slot)
    {

    }
	
	protected int getStackLimit(int slot, @Nonnull ItemStack stack)
    {
		if (ItemSlotTypesEnum.checkMaskEquals(stackTypes.get(slot), ItemSlotTypesEnum.UPGRADE))
			return 1;
		
        return Math.min(getSlotLimit(slot), stack.getMaxStackSize());
    }
	
	public void setStack(NonNullList<ItemStack> stacks) {
    	this.stacks = stacks;
    }
}
