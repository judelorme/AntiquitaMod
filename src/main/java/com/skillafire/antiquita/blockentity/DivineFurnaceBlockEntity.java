package com.skillafire.antiquita.blockentity;

import com.skillafire.antiquita.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DivineFurnaceBlockEntity extends BlockEntity implements IAnimatable {
	private AnimationFactory factory = new AnimationFactory(this);
	
	public DivineFurnaceBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.DIVINE_FURNACE_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::predicate));
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.divinefurnace.crafting", true));
		return PlayState.CONTINUE;
	}
	
	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
