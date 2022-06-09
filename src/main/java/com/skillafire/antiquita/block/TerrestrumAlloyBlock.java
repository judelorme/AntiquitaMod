package com.skillafire.antiquita.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class TerrestrumAlloyBlock extends Block {
	public static final Properties props = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN)
			.strength(3.0f)
			.sound(SoundType.METAL);
		
	public TerrestrumAlloyBlock() {
		super(props);
	}
}
