package com.skillafire.antiquita.integration.JEI;

import java.util.List;
import java.util.Objects;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.recipe.DivineFurnaceRecipe;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIAntiquitaPlugin implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Antiquita.MOD_ID, "jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new DivineFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		@SuppressWarnings("resource")
		RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

		List<DivineFurnaceRecipe> worktableRecipes = rm
				.getAllRecipesFor(DivineFurnaceRecipe.DivineFurnaceRecipeType.INSTANCE);
		registration.addRecipes(new RecipeType<>(DivineFurnaceRecipeCategory.UID, DivineFurnaceRecipe.class),
				worktableRecipes);
	}
}
