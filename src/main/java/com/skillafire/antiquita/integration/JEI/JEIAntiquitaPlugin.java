package com.skillafire.antiquita.integration.JEI;

import java.util.List;
import java.util.Objects;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.recipe.DivineAnvilRecipe;
import com.skillafire.antiquita.recipe.DivineFurnaceRecipe;
import com.skillafire.antiquita.recipe.DivineSmelteryRecipe;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class JEIAntiquitaPlugin implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Antiquita.MOD_ID, "jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new DivineFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new DivineAnvilRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new DivineSmelteryRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		@SuppressWarnings("resource")
		RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

		List<DivineFurnaceRecipe> divineFurnaceRecipes = rm
				.getAllRecipesFor(DivineFurnaceRecipe.DivineFurnaceRecipeType.INSTANCE);
		registration.addRecipes(new RecipeType<>(DivineFurnaceRecipeCategory.UID, DivineFurnaceRecipe.class),
				divineFurnaceRecipes);

		List<DivineAnvilRecipe> divineAnvilRecipes = rm
				.getAllRecipesFor(DivineAnvilRecipe.DivineAnvilRecipeType.INSTANCE);
		registration.addRecipes(new RecipeType<>(DivineAnvilRecipeCategory.UID, DivineAnvilRecipe.class),
				divineAnvilRecipes);
		
		List<DivineSmelteryRecipe> divineSmelteryRecipes = rm
				.getAllRecipesFor(DivineSmelteryRecipe.DivineSmelteryRecipeType.INSTANCE);
		registration.addRecipes(new RecipeType<>(DivineSmelteryRecipeCategory.UID, DivineSmelteryRecipe.class),
				divineSmelteryRecipes);
	}
}
