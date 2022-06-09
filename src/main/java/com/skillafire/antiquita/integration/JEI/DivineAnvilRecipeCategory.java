package com.skillafire.antiquita.integration.JEI;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.init.BlockInit;
import com.skillafire.antiquita.recipe.DivineAnvilRecipe;
import com.skillafire.antiquita.screen.DivineAnvilMenu;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.drawable.IDrawableBuilder;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.gui.elements.DrawableBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class DivineAnvilRecipeCategory implements IRecipeCategory<DivineAnvilRecipe> {
	public final static ResourceLocation UID = new ResourceLocation(Antiquita.MOD_ID, DivineAnvilRecipe.DivineAnvilRecipeType.ID);
	public final static ResourceLocation TEXTURE = new ResourceLocation(Antiquita.MOD_ID, "textures/gui/divine_anvil_gui.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	protected final IDrawableAnimated arrow;
	
	private final int TrimTop = 10;
	private final int TrimLeft = 25;
	private final int TrimRight = 30;
	
	public DivineAnvilRecipeCategory(IGuiHelper helper) {
		IDrawableBuilder builder = new DrawableBuilder(TEXTURE, 0, 0, 176, 75);
		builder.trim(TrimTop, 0, TrimLeft, TrimRight);
		
		this.background = builder.build();
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(BlockInit.DIVINE_ANVIL.get().asItem()));
		
		IDrawableStatic d1 = helper.createDrawable(TEXTURE, 176, 0, DivineAnvilMenu.TOTAL_PROGRESS_PIXELS, 15);
		arrow = helper.createAnimatedDrawable(d1, 400, StartDirection.LEFT, false);
	}
	
	@Override
	public void draw(DivineAnvilRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX,
			double mouseY) {
		IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
		arrow.draw(stack, 90 - TrimLeft, 35 - TrimTop);
	}
	
	@Override
	public Component getTitle() {
		return new TextComponent(new TranslatableComponent("block.antiquita.divine_anvil").getString());
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public Class<? extends DivineAnvilRecipe> getRecipeClass() {
		return DivineAnvilRecipe.class;
	}
	
	@Override
	public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull DivineAnvilRecipe recipe, @Nonnull IFocusGroup ingredients) {
		builder.addSlot(RecipeIngredientRole.INPUT, 30 - TrimLeft, 17 - TrimTop).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 48 - TrimLeft, 17 - TrimTop).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 66 - TrimLeft, 17 - TrimTop).addIngredients(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.INPUT, 30 - TrimLeft, 35 - TrimTop).addIngredients(recipe.getIngredients().get(3));
		builder.addSlot(RecipeIngredientRole.INPUT, 48 - TrimLeft, 35 - TrimTop).addIngredients(recipe.getIngredients().get(4));
		builder.addSlot(RecipeIngredientRole.INPUT, 66 - TrimLeft, 35 - TrimTop).addIngredients(recipe.getIngredients().get(5));
		builder.addSlot(RecipeIngredientRole.INPUT, 30 - TrimLeft, 53 - TrimTop).addIngredients(recipe.getIngredients().get(6));
		builder.addSlot(RecipeIngredientRole.INPUT, 48 - TrimLeft, 53 - TrimTop).addIngredients(recipe.getIngredients().get(7));
		builder.addSlot(RecipeIngredientRole.INPUT, 66 - TrimLeft, 53 - TrimTop).addIngredients(recipe.getIngredients().get(8));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - TrimLeft, 35 - TrimTop).addItemStack(recipe.getResultItem());
	}
}
