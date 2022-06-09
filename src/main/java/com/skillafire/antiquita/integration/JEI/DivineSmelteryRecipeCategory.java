package com.skillafire.antiquita.integration.JEI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.init.BlockInit;
import com.skillafire.antiquita.recipe.DivineSmelteryRecipe;
import com.skillafire.antiquita.screen.DivineSmelteryMenu;
import com.skillafire.antiquita.utils.ModTags;

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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class DivineSmelteryRecipeCategory implements IRecipeCategory<DivineSmelteryRecipe> {
	public final static ResourceLocation UID = new ResourceLocation(Antiquita.MOD_ID, DivineSmelteryRecipe.DivineSmelteryRecipeType.ID);
	public final static ResourceLocation TEXTURE = new ResourceLocation(Antiquita.MOD_ID, "textures/gui/divine_smeltery_gui.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	protected final IDrawableAnimated arrow;
	
	private final int TrimTop = 10;
	private final int TrimLeft = 25;
	private final int TrimRight = 30;
	
	public DivineSmelteryRecipeCategory(IGuiHelper helper) {
		IDrawableBuilder builder = new DrawableBuilder(TEXTURE, 0, 0, 176, 75);
		builder.trim(TrimTop, 0, TrimLeft, TrimRight);
		
		this.background = builder.build();
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(BlockInit.DIVINE_SMELTERY.get().asItem()));
		
		IDrawableStatic d1 = helper.createDrawable(TEXTURE, 176, 14, DivineSmelteryMenu.TOTAL_PROGRESS_PIXELS, 15);
		arrow = helper.createAnimatedDrawable(d1, 400, StartDirection.LEFT, false);
	}
	
	@Override
	public void draw(DivineSmelteryRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX,
			double mouseY) {
		IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
		arrow.draw(stack, 80 - TrimLeft, 35 - TrimTop);
	}
	
	@Override
	public Component getTitle() {
		return new TextComponent(new TranslatableComponent("block.antiquita.divine_smeltery").getString());
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
	public Class<? extends DivineSmelteryRecipe> getRecipeClass() {
		return DivineSmelteryRecipe.class;
	}
	
	@Override
	public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull DivineSmelteryRecipe recipe, @Nonnull IFocusGroup ingredients) {
		List<ItemStack> upgrades = new ArrayList<>();
		upgrades.add(ItemStack.EMPTY);
		Iterator<Item> upgradesIterator = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.DIVINE_SMELTERY_UPGRADE).iterator();
		while (upgradesIterator.hasNext()) {
			Item item = upgradesIterator.next();
			upgrades.add(new ItemStack(item));
		}
			
		List<ItemStack> fuels = new ArrayList<>();		
		Iterator<Item> fuelsIterator = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.DIVINE_SMELTERY_FUEL).iterator();
		while (fuelsIterator.hasNext()) {
			Item item = fuelsIterator.next();
			fuels.add(new ItemStack(item));
		}
		
		builder.addSlot(RecipeIngredientRole.INPUT, 56 - TrimLeft, 17 - TrimTop).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.CATALYST, 33 - TrimLeft, 35 - TrimTop).addItemStacks(upgrades);
		builder.addSlot(RecipeIngredientRole.INPUT, 56 - TrimLeft, 53 - TrimTop).addItemStacks(fuels);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 116 - TrimLeft, 35 - TrimTop).addItemStack(recipe.getResultItem());
	}
}
