package com.skillafire.antiquita.blockentity.common;

import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class MachineItemStackHandler extends BaseItemStackHandler implements IItemHandler, IItemHandlerModifiable {
    public MachineItemStackHandler(NonNullList<ItemStack> stacks, Map<Integer, Integer> stackTypes)
    {
        super(stacks, stackTypes);
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
    	if (ItemSlotTypesEnum.checkMaskNotEquals(stackTypes.get(slot), ItemSlotTypesEnum.OUTPUT))
    		return ItemStack.EMPTY;
    	
        return super.extractItem(slot, amount, simulate);
    }
}
