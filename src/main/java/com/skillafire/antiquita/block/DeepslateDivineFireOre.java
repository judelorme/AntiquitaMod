package com.skillafire.antiquita.block;

import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class DeepslateDivineFireOre extends OreBlock {
	public static final Properties props = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN)
			.strength(1.5f)
			.sound(SoundType.METAL)
			.requiresCorrectToolForDrops()
			.emissiveRendering((state, getter, pos) -> true);
		
	public DeepslateDivineFireOre() {
		super(props);
	}
}
