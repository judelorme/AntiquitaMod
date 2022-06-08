package com.skillafire.antiquita.event;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.init.BlockEntityInit;
import com.skillafire.antiquita.renderer.DivineFurnaceRenderer;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Antiquita.MOD_ID, bus = Bus.MOD)
public class ModEventBusEvents {
	@SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityInit.DIVINE_FURNACE_BLOCK_ENTITY.get(), DivineFurnaceRenderer::new);
    }
}
