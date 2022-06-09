package com.skillafire.antiquita.recipe;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.blockentity.DivineFurnaceBlockEntity;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class DivineFurnaceRecipe implements Recipe<SimpleContainer> {
	private final ResourceLocation id;
	private final ItemStack output;
	private final NonNullList<Ingredient> recipeItems;
	
	public DivineFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
		this.id = id;
		this.output = output;
		this.recipeItems = recipeItems;
	}
	
	@Override
	public boolean matches(SimpleContainer container, Level level) {
		for (int i = 0; i < recipeItems.size(); i++) {
			if (!recipeItems.get(i).test(container.getItem(i)))
				return false;
		}
		return true;
	}

	@Override
	public ItemStack assemble(SimpleContainer p_44001_) {
		return output;
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return output.copy();
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return recipeItems;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return DivineFurnaceRecipeSerializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return DivineFurnaceRecipeType.INSTANCE;
	}

	public static class DivineFurnaceRecipeType implements RecipeType<DivineFurnaceRecipe> {
		private DivineFurnaceRecipeType() {}
		
		public static final DivineFurnaceRecipeType INSTANCE = new DivineFurnaceRecipeType();
		public static final String ID = "divine_furnace_recipes";
	}
	
	public static class DivineFurnaceRecipeSerializer implements RecipeSerializer<DivineFurnaceRecipe> {
        public static final DivineFurnaceRecipeSerializer INSTANCE = new DivineFurnaceRecipeSerializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Antiquita.MOD_ID, DivineFurnaceRecipeType.ID);

        @Override
        public DivineFurnaceRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(DivineFurnaceBlockEntity.OUTPUT_SLOT_NUMBER, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new DivineFurnaceRecipe(id, output, inputs);
        }

        @Override
        public DivineFurnaceRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new DivineFurnaceRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, DivineFurnaceRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return DivineFurnaceRecipeSerializer.castClass(RecipeSerializer.class);
        }

        @SuppressWarnings("unchecked")
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
	}
}
