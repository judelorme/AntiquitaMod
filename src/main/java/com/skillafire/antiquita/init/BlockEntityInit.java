package com.skillafire.antiquita.init;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.blockentity.DivineAnvilBlockEntity;
import com.skillafire.antiquita.blockentity.DivineFurnaceBlockEntity;
import com.skillafire.antiquita.blockentity.DivineSmelteryBlockEntity;

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
	
	public static final RegistryObject<BlockEntityType<DivineAnvilBlockEntity>> DIVINE_ANVIL_BLOCK_ENTITY = ENTITY_TYPES
			.register("divine_anvil", () -> BlockEntityType.Builder
					.of(DivineAnvilBlockEntity::new, BlockInit.DIVINE_ANVIL.get()).build(null));

	public static final RegistryObject<BlockEntityType<DivineSmelteryBlockEntity>> DIVINE_SMELTERY_BLOCK_ENTITY = ENTITY_TYPES
			.register("divine_smeltery", () -> BlockEntityType.Builder
					.of(DivineSmelteryBlockEntity::new, BlockInit.DIVINE_SMELTERY.get()).build(null));
	
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
}
