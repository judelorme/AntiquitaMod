package com.skillafire.antiquita;

import com.skillafire.antiquita.init.BlockEntityInit;
import com.skillafire.antiquita.init.BlockInit;
import com.skillafire.antiquita.init.ItemInit;
import com.skillafire.antiquita.init.MenuTypesInit;
import com.skillafire.antiquita.init.RecipeInit;
import com.skillafire.antiquita.screen.DivineAnvilScreen;
import com.skillafire.antiquita.screen.DivineFurnaceScreen;
import com.skillafire.antiquita.screen.DivineSmelteryScreen;

import net.minecraft.client.gui.screens.MenuScreens;
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
import software.bernie.geckolib3.GeckoLib;

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
		MenuTypesInit.register(bus);
		RecipeInit.register(bus);

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		
		MinecraftForge.EVENT_BUS.register(this);
		
		GeckoLib.initialize();
	}
	
	private void clientSetup(final FMLClientSetupEvent event) {
		// Crops
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SORGHUM_PLANT.get(), RenderType.cutout());
		
		// Flowers
		/*ItemBlockRenderTypes.setRenderLayer(BlockInit.STARGONIA_FLOWER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.POTTED_STARGONIA_FLOWER.get(), RenderType.cutout());*/
		
		// Trees		
		/*ItemBlockRenderTypes.setRenderLayer(BlockInit.SKY_TREE_LEAVES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SKY_TREE_SAPLING.get(), RenderType.cutout());*/
		
		// Entities
		ItemBlockRenderTypes.setRenderLayer(BlockInit.DIVINE_FURNACE.get(), RenderType.cutout());
		/*ItemBlockRenderTypes.setRenderLayer(ItemInit.ITEMS.getEntries()., RenderType.translucent());*/
		
		// Menus
		MenuScreens.register(MenuTypesInit.DIVINE_FURNACE_MENU.get(), DivineFurnaceScreen::new);
		MenuScreens.register(MenuTypesInit.DIVINE_ANVIL_MENU.get(), DivineAnvilScreen::new);
		MenuScreens.register(MenuTypesInit.DIVINE_SMELTERY_MENU.get(), DivineSmelteryScreen::new);
		/*MenuScreens.register(MenuTypesInit.SKY_FURNACE_MENU.get(), SkyFurnaceScreen::new);*/
		
		// Living Entities
		/*EntityRenderers.register(EntityInit.ALIEN.get(), AlienRenderer::new);*/
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		/*event.enqueueWork(() -> {
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.STARGONIA_FLOWER.getId(), BlockInit.POTTED_STARGONIA_FLOWER); 
		});	*/
	}
}
