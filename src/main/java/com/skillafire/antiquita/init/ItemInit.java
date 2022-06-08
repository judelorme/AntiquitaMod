package com.skillafire.antiquita.init;

import com.google.common.base.Supplier;
import com.skillafire.antiquita.Antiquita;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Antiquita.MOD_ID);
	
	public static final RegistryObject<Item> RAW_ORICHALCUM_ORE = register("raw_orichalcum_ore", () -> new Item(new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<Item> ORICHALCUM_INGOT = register("orichalcum_ingot", () -> new Item(new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Item> DIVINE_FIRE_POWDER = register("divine_fire_powder", () -> new Item(new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));

	// Tools
	/*public static final RegistryObject<PickaxeItem> KAMACITE_PICKAXE = register("kamacite_pickaxe", () -> new PickaxeItem(TierInit.KAMACITE, 0, 0f, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<AxeItem> KAMACITE_AXE = register("kamacite_axe", () -> new AxeItem(TierInit.KAMACITE, 0, 0f, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<HoeItem> KAMACITE_HOE = register("kamacite_hoe", () -> new HoeItem(TierInit.KAMACITE, 0, 0f, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<ShovelItem> KAMACITE_SHOVEL = register("kamacite_shovel", () -> new ShovelItem(TierInit.KAMACITE, 0, 0f, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<PickaxeItem> TERRALITE_PICKAXE = register("terralite_pickaxe", () -> new PickaxeItem(TierInit.TERRALITE, 0, 0f, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<AxeItem> TERRALITE_AXE = register("terralite_axe", () -> new AxeItem(TierInit.TERRALITE, 0, 0f, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<HoeItem> TERRALITE_HOE = register("terralite_hoe", () -> new HoeItem(TierInit.TERRALITE, 0, 0f, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<ShovelItem> TERRALITE_SHOVEL = register("terralite_shovel", () -> new ShovelItem(TierInit.TERRALITE, 0, 0f, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));*/
	
	// Weapons
	/*public static final RegistryObject<SwordItem> KAMACITE_SWORD = register("kamacite_sword", () -> new SwordItem(TierInit.KAMACITE, 3, -1.4F, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<SwordItem> TERRALITE_SWORD = register("terralite_sword", () -> new SwordItem(TierInit.TERRALITE, 4, -1.2F, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));*/
	
	// Armors
	/*public static final RegistryObject<ArmorItem> KAMACITE_HEAD = register("kamacite_head", () -> new ArmorItem(ArmorMaterialInit.KAMACITE, EquipmentSlot.HEAD, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<ArmorItem> KAMACITE_BODY = register("kamacite_body", () -> new ArmorItem(ArmorMaterialInit.KAMACITE, EquipmentSlot.CHEST, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<ArmorItem> KAMACITE_LEGGINGS = register("kamacite_leggings", () -> new ArmorItem(ArmorMaterialInit.KAMACITE, EquipmentSlot.LEGS, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<ArmorItem> KAMACITE_BOOTS = register("kamacite_boots", () -> new ArmorItem(ArmorMaterialInit.KAMACITE, EquipmentSlot.FEET, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<ArmorItem> TERRALITE_HEAD = register("terralite_head", () -> new ArmorItem(ArmorMaterialInit.TERRALITE, EquipmentSlot.HEAD, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<ArmorItem> TERRALITE_BODY = register("terralite_body", () -> new ArmorItem(ArmorMaterialInit.TERRALITE, EquipmentSlot.CHEST, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<ArmorItem> TERRALITE_LEGGINGS = register("terralite_leggings", () -> new ArmorItem(ArmorMaterialInit.TERRALITE, EquipmentSlot.LEGS, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<ArmorItem> TERRALITE_BOOTS = register("terralite_boots", () -> new ArmorItem(ArmorMaterialInit.TERRALITE, EquipmentSlot.FEET, new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));*/
	
	// Items
	/*public static final RegistryObject<Item> RAW_KAMACITE_ORE = register("raw_kamacite_ore", () -> new Item(new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<Item> KAMACITE_INGOT = register("kamacite_ingot", () -> new Item(new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<Item> RAW_TERRALITE_ORE = register("raw_terralite_ore", () -> new Item(new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<Item> TERRALITE_INGOT = register("terralite_ingot", () -> new Item(new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<Item> TERRALITE_SHEET = register("terralite_sheet", () -> new Item(new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));*/
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITEMS.register(name, item);
	}
	
	public static void register(IEventBus bus) {
		ITEMS.register(bus);
	}
}
