package com.skillafire.antiquita.world.feature;

import com.skillafire.antiquita.block.DivineFireOre;
import com.skillafire.antiquita.block.OrichalcumOre;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
	
	// Blocks
	public static final Holder<PlacedFeature> ORICHALCUM_ORE_PLACED = PlacementUtils.register("orichalcum_ore_placed", 
			ModConfiguredFeatures.ORICHALCUM_ORE,
			ModOrePlacement.commonOrePlacement(OrichalcumOre.AMOUNT_PER_CHUNK, 
					HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(OrichalcumOre.MIN_HEIGHT), VerticalAnchor.aboveBottom(OrichalcumOre.MAX_HEIGHT))));
	
	public static final Holder<PlacedFeature> DIVINE_FIRE_ORE_PLACED = PlacementUtils.register("divine_fire_ore_placed", 
			ModConfiguredFeatures.DIVINE_FIRE_ORE,
			ModOrePlacement.commonOrePlacement(DivineFireOre.AMOUNT_PER_CHUNK, 
					HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(DivineFireOre.MIN_HEIGHT), VerticalAnchor.aboveBottom(DivineFireOre.MAX_HEIGHT))));
	
	/*public static final Holder<PlacedFeature> TERRALITE_ORE_PLACED = PlacementUtils.register("terralite_ore_placed", 
			ModConfiguredFeatures.TERRALITE_ORE,
			ModOrePlacement.commonOrePlacement(TerraliteOre.AMOUNT_PER_CHUNK, 
					HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(TerraliteOre.MIN_HEIGHT), VerticalAnchor.aboveBottom(TerraliteOre.MAX_HEIGHT))));*/
	
	// Flowers
	/*public static final Holder<PlacedFeature> STARGONIA_PLACED = PlacementUtils.register("stargonia_placed", 
			ModConfiguredFeatures.STARGONIA,
			RarityFilter.onAverageOnceEvery(16),
			InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP,
			BiomeFilter.biome()
			);*/
	
	// Trees
	/*public static final Holder<PlacedFeature> SKY_TREE_PLACED = PlacementUtils.register("sky_tree_placed", 
			ModConfiguredFeatures.SKY_TREE_SPAWN, 
			VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.02f, 1)));*/
}
