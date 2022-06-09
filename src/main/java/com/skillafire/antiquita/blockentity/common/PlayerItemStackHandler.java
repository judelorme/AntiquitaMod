package com.skillafire.antiquita.blockentity.common;

import java.util.Map;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class PlayerItemStackHandler extends BaseItemStackHandler implements IItemHandler, IItemHandlerModifiable {
	public PlayerItemStackHandler(NonNullList<ItemStack> stacks, Map<Integer, Integer> stackTypes)
    {
        super(stacks, stackTypes);
    }
}
