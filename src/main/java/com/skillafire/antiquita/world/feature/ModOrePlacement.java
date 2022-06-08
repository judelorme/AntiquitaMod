package com.skillafire.antiquita.world.feature;

import java.util.List;

import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModOrePlacement {
	public static List<PlacementModifier> orePlacement(PlacementModifier modifier1, PlacementModifier modifier2) {
		return List.of(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
	}
	
	public static List<PlacementModifier> commonOrePlacement(int amount, PlacementModifier modifier) {
		return orePlacement(CountPlacement.of(amount), modifier);
	}

	public static List<PlacementModifier> rareOrePlacement(int amount, PlacementModifier modifier) {
		return orePlacement(RarityFilter.onAverageOnceEvery(amount), modifier);
	}
}
