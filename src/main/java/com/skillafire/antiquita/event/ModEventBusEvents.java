package com.skillafire.antiquita.event;

import javax.annotation.Nonnull;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.event.loot.SorghumSeedsFromGrassModifier;
import com.skillafire.antiquita.init.BlockEntityInit;
import com.skillafire.antiquita.renderer.DivineFurnaceRenderer;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Antiquita.MOD_ID, bus = Bus.MOD)
public class ModEventBusEvents {
	@SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityInit.DIVINE_FURNACE_BLOCK_ENTITY.get(), DivineFurnaceRenderer::new);
    }
	
	@SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(new SorghumSeedsFromGrassModifier.Serializer().setRegistryName(Antiquita.MOD_ID, "sorghum_seeds_from_grass"));
    }
}
