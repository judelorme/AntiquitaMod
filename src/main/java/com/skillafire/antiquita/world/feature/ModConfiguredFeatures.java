package com.skillafire.antiquita.world.feature;

import java.util.List;

import com.skillafire.antiquita.block.DivineFireOre;
import com.skillafire.antiquita.block.OrichalcumOre;
import com.skillafire.antiquita.init.BlockInit;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class ModConfiguredFeatures {

	// Blocks
	public static final List<OreConfiguration.TargetBlockState> OVERWORLD_ORICHALCUM_ORES = 
			List.of(
					OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.ORICHALCUM_ORE.get().defaultBlockState()),
					OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEEPSLATE_ORICHALCUM_ORE.get().defaultBlockState()));
	
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORICHALCUM_ORE = FeatureUtils.register("ore_orichalcum", Feature.ORE, new OreConfiguration(OVERWORLD_ORICHALCUM_ORES, OrichalcumOre.AMOUNT_PER_VEIN));
	
	public static final List<OreConfiguration.TargetBlockState> OVERWORLD_DIVINE_FIRE_ORES = 
			List.of(
					OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.DIVINE_FIRE_ORE.get().defaultBlockState()),
					OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEEPSLATE_DIVINE_FIRE_ORE.get().defaultBlockState()));
	
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> DIVINE_FIRE_ORE = FeatureUtils.register("ore_divine_fire", Feature.ORE, new OreConfiguration(OVERWORLD_DIVINE_FIRE_ORES, DivineFireOre.AMOUNT_PER_VEIN));
	// Flowers
	/*public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> STARGONIA = FeatureUtils.register("flower_stargonia", Feature.FLOWER,
			new RandomPatchConfiguration(32, 6, 2, 
					PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockInit.STARGONIA_FLOWER.get())))));*/
	
	// Trees
	/*public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SKY_TREE = FeatureUtils.register("sky_tree", Feature.TREE, 
			new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.SKY_TREE_LOG.get()),
			new StraightTrunkPlacer(6, 1, 2), 
			BlockStateProvider.simple(BlockInit.SKY_TREE_LEAVES.get()), 
			new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4), 
			new TwoLayersFeatureSize(2, 3, 2)).build());
	
	public static final Holder<PlacedFeature> SKY_TREE_CHECKED = PlacementUtils.register("sky_tree_checked", SKY_TREE, 
			PlacementUtils.filteredByBlockSurvival(BlockInit.SKY_TREE_SAPLING.get()));
	
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> SKY_TREE_SPAWN = FeatureUtils.register("sky_tree_spawn", Feature.RANDOM_SELECTOR, 
			new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(SKY_TREE_CHECKED, 0.2F)), SKY_TREE_CHECKED));*/
}
