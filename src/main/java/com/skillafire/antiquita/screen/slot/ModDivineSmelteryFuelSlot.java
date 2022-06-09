package com.skillafire.antiquita.screen.slot;

import com.skillafire.antiquita.utils.ModTags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModDivineSmelteryFuelSlot extends SlotItemHandler {

	public ModDivineSmelteryFuelSlot(IItemHandler itemHandler, int index, int x, int y) {
		super(itemHandler, index, x, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.is(ModTags.Items.DIVINE_SMELTERY_FUEL);
	}
}
