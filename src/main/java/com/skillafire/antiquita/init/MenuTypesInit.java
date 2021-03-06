package com.skillafire.antiquita.init;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.screen.DivineAnvilMenu;
import com.skillafire.antiquita.screen.DivineFurnaceMenu;
import com.skillafire.antiquita.screen.DivineSmelteryMenu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypesInit {
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Antiquita.MOD_ID);
	
	public static final RegistryObject<MenuType<DivineFurnaceMenu>> DIVINE_FURNACE_MENU = registerMenuType(DivineFurnaceMenu::new, "divine_furnace_menu");
	public static final RegistryObject<MenuType<DivineAnvilMenu>> DIVINE_ANVIL_MENU = registerMenuType(DivineAnvilMenu::new, "divine_anvil_menu");
	public static final RegistryObject<MenuType<DivineSmelteryMenu>> DIVINE_SMELTERY_MENU = registerMenuType(DivineSmelteryMenu::new, "divine_smeltery_menu");
		
	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
		return MENUS.register(name, () -> IForgeMenuType.create(factory));
	}
	
	public static void register(IEventBus bus) {
		MENUS.register(bus);
	}
}
