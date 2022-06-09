package com.skillafire.antiquita.init;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.recipe.DivineAnvilRecipe;
import com.skillafire.antiquita.recipe.DivineFurnaceRecipe;
import com.skillafire.antiquita.recipe.DivineSmelteryRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = 
			DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Antiquita.MOD_ID);
	
	public static final RegistryObject<RecipeSerializer<DivineFurnaceRecipe>> DIVINE_FURNACE_SERIALIZER = 
			SERIALIZERS.register("divine_furnace_recipes", () -> DivineFurnaceRecipe.DivineFurnaceRecipeSerializer.INSTANCE);
	
	public static final RegistryObject<RecipeSerializer<DivineAnvilRecipe>> DIVINE_ANVIL_SERIALIZER = 
			SERIALIZERS.register("divine_anvil_recipes", () -> DivineAnvilRecipe.DivineAnvilRecipeSerializer.INSTANCE);
	
	public static final RegistryObject<RecipeSerializer<DivineSmelteryRecipe>> DIVINE_SMELTERY_SERIALIZER = 
			SERIALIZERS.register("divine_smeltery_recipes", () -> DivineSmelteryRecipe.DivineSmelteryRecipeSerializer.INSTANCE);
	
	public static void register(IEventBus bus) {
		SERIALIZERS.register(bus);
	}
}
