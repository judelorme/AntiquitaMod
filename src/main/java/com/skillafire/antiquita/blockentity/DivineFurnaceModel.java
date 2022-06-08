package com.skillafire.antiquita.blockentity;

import com.skillafire.antiquita.Antiquita;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DivineFurnaceModel extends AnimatedGeoModel<DivineFurnaceBlockEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(DivineFurnaceBlockEntity animatable) {
		return new ResourceLocation(Antiquita.MOD_ID, "animations/divinefurnace.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(DivineFurnaceBlockEntity object) {
		return new ResourceLocation(Antiquita.MOD_ID, "geo/divinefurnace.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(DivineFurnaceBlockEntity object) {
		return new ResourceLocation(Antiquita.MOD_ID, "textures/block/divine_furnace.png");
	}
}
