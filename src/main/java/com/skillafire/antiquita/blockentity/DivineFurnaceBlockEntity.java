package com.skillafire.antiquita.blockentity;

import com.skillafire.antiquita.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DivineFurnaceBlockEntity extends BlockEntity implements IAnimatable {
	private AnimationFactory factory = new AnimationFactory(this);
	private static int currentState = 0;
	private static Boolean hasFilled = false;
	
	public DivineFurnaceBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.DIVINE_FURNACE_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::predicate));
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (currentState == 0 && event.getController().getCurrentAnimation() != null) {
		}
		
		if (currentState == 1 && !hasFilled) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.divinefurnace.fill", false));
			hasFilled = true;
			return PlayState.CONTINUE;
		}
		
		if (currentState == 1 && hasFilled) {
			if (event.getController().getAnimationState() == AnimationState.Stopped)
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.divinefurnace.crafting", true));
		}
		
		if (currentState == 2) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.divinefurnace.empty", false));
			currentState = 0;
			hasFilled = false;
			return PlayState.CONTINUE;
		}
		
		return PlayState.CONTINUE;
	}
	
	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
	
	public void PlayAnimation() {
		if (currentState == 0)
			currentState = 1;
		else if (currentState == 1)
			currentState = 2;
		else if (currentState == 2)
			currentState = 0;
	}
	
}
