package com.skillafire.antiquita.integration.JEI;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.init.BlockInit;
import com.skillafire.antiquita.recipe.DivineFurnaceRecipe;
import com.skillafire.antiquita.screen.DivineFurnaceMenu;

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

public class DivineFurnaceRecipeCategory implements IRecipeCategory<DivineFurnaceRecipe> {
	public final static ResourceLocation UID = new ResourceLocation(Antiquita.MOD_ID, DivineFurnaceRecipe.DivineFurnaceRecipeType.ID);
	public final static ResourceLocation TEXTURE = new ResourceLocation(Antiquita.MOD_ID, "textures/gui/divine_furnace_gui.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	protected final IDrawableAnimated arrow;
	
	private final int TrimTop = 10;
	private final int TrimLeft = 25;
	private final int TrimRight = 30;
	
	public DivineFurnaceRecipeCategory(IGuiHelper helper) {
		IDrawableBuilder builder = new DrawableBuilder(TEXTURE, 0, 0, 176, 75);
		builder.trim(TrimTop, 0, TrimLeft, TrimRight);
		
		this.background = builder.build();
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(BlockInit.DIVINE_FURNACE.get().asItem()));
		
		IDrawableStatic d1 = helper.createDrawable(TEXTURE, 176, 0, DivineFurnaceMenu.TOTAL_PROGRESS_PIXELS, 15);
		arrow = helper.createAnimatedDrawable(d1, 400, StartDirection.LEFT, false);
	}
	
	@Override
	public void draw(DivineFurnaceRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX,
			double mouseY) {
		IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
		arrow.draw(stack, 86 - TrimLeft, 34 - TrimTop);
	}
	
	@Override
	public Component getTitle() {
		return new TextComponent(new TranslatableComponent("block.antiquita.divine_furnace").getString());
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
	public Class<? extends DivineFurnaceRecipe> getRecipeClass() {
		return DivineFurnaceRecipe.class;
	}
	
	@Override
	public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull DivineFurnaceRecipe recipe, @Nonnull IFocusGroup ingredients) {
		builder.addSlot(RecipeIngredientRole.INPUT, 35 - TrimLeft, 19 - TrimTop).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 54 - TrimLeft, 19 - TrimTop).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 120 - TrimLeft, 34 - TrimTop).addItemStack(recipe.getResultItem());
	}
}
