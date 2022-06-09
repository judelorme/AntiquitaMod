package com.skillafire.antiquita.init;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.recipe.DivineFurnaceRecipe;

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
	
	public static void register(IEventBus bus) {
		SERIALIZERS.register(bus);
	}
}
