package com.skillafire.antiquita;

import com.skillafire.antiquita.init.BlockEntityInit;
import com.skillafire.antiquita.init.BlockInit;
import com.skillafire.antiquita.init.ItemInit;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("antiquita")
public class Antiquita {
	public static final String MOD_ID = "antiquita";

	public static final CreativeModeTab ANTIQUITA_TAB = new CreativeModeTab(MOD_ID) {

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(BlockInit.ORICHALCUM_ORE.get());
		}
	};
	
	public Antiquita() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
				
		ItemInit.register(bus);
		BlockInit.register(bus);
		BlockEntityInit.register(bus);

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void clientSetup(final FMLClientSetupEvent event) {
		// Flowers
		/*ItemBlockRenderTypes.setRenderLayer(BlockInit.STARGONIA_FLOWER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.POTTED_STARGONIA_FLOWER.get(), RenderType.cutout());*/
		
		// Trees		
		/*ItemBlockRenderTypes.setRenderLayer(BlockInit.SKY_TREE_LEAVES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SKY_TREE_SAPLING.get(), RenderType.cutout());*/
		
		// Entities
		ItemBlockRenderTypes.setRenderLayer(BlockInit.DIVINE_FURNACE.get(), RenderType.translucent());
		
		// Menus
		/*MenuScreens.register(MenuTypesInit.WORKTABLE_MENU.get(), WorktableScreen::new);
		MenuScreens.register(MenuTypesInit.SKY_FURNACE_MENU.get(), SkyFurnaceScreen::new);*/
		
		// Living Entities
		/*EntityRenderers.register(EntityInit.ALIEN.get(), AlienRenderer::new);*/
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		/*event.enqueueWork(() -> {
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.STARGONIA_FLOWER.getId(), BlockInit.POTTED_STARGONIA_FLOWER); 
		});	*/
	}
}
