package com.skillafire.antiquita.init;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.blockentity.DivineFurnaceBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
	public static final DeferredRegister<BlockEntityType<?>> ENTITY_TYPES = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITIES, Antiquita.MOD_ID);

	public static final RegistryObject<BlockEntityType<DivineFurnaceBlockEntity>> DIVINE_FURNACE_BLOCK_ENTITY = ENTITY_TYPES
			.register("divine_furnace", () -> BlockEntityType.Builder
					.of(DivineFurnaceBlockEntity::new, BlockInit.DIVINE_FURNACE.get()).build(null));

	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
}
