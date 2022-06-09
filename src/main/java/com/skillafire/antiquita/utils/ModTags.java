package com.skillafire.antiquita.utils;

import com.skillafire.antiquita.Antiquita;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
	public static class Blocks {
		public static final TagKey<Block> ORES = tag("ores");

		private static TagKey<Block> tag(String name) {
			return BlockTags.create(new ResourceLocation(Antiquita.MOD_ID, name));
		}

		private static TagKey<Block> forgeTag(String name) {
			return BlockTags.create(new ResourceLocation("forge", name));
		}
	}

	public static class Items {
		public static final TagKey<Item> DIVINE_SMELTERY_FUEL = tag("divine_smeltery_fuel");
		public static final TagKey<Item> DIVINE_SMELTERY_UPGRADE = tag("divine_smeltery_upgrade");
				
		private static TagKey<Item> tag(String name) {
			return ItemTags.create(new ResourceLocation(Antiquita.MOD_ID, name));
		}

		private static TagKey<Item> forgeTag(String name) {
			return ItemTags.create(new ResourceLocation("forge", name));
		}
	}
}
