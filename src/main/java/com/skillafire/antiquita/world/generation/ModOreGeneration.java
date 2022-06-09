package com.skillafire.antiquita.world.generation;

import java.util.List;

import com.skillafire.antiquita.world.feature.ModPlacedFeatures;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModOreGeneration {
	public static void generateOres(final BiomeLoadingEvent event) {
		List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
		
		base.add(ModPlacedFeatures.ORICHALCUM_ORE_PLACED);
		base.add(ModPlacedFeatures.VANADIUM_ORE_PLACED);
		base.add(ModPlacedFeatures.DIVINE_FIRE_ORE_PLACED);
	}
}
