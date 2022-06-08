package com.skillafire.antiquita.block;

import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class OrichalcumOre extends OreBlock {

	public static final int AMOUNT_PER_CHUNK = 40;
	public static final int AMOUNT_PER_VEIN = 6;
	public static final int MIN_HEIGHT = -64;
	public static final int MAX_HEIGHT = 100;
	
	public static final Properties props = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN)
			.strength(3.0f)
			.sound(SoundType.METAL)
			.requiresCorrectToolForDrops()
			.emissiveRendering((state, getter, pos) -> true);
		
	public OrichalcumOre() {
		super(props);
		
	}
}
