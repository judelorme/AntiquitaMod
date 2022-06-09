package com.skillafire.antiquita.recipe;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.blockentity.DivineAnvilBlockEntity;

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

public class DivineAnvilRecipe implements Recipe<SimpleContainer> {
	private final ResourceLocation id;
	private final ItemStack output;
	private final NonNullList<Ingredient> recipeItems;
	
	public DivineAnvilRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
		this.id = id;
		this.output = output;
		this.recipeItems = recipeItems;
	}
	
	@Override
	public boolean matches(SimpleContainer container, Level level) {
		for (int i = 0; i < DivineAnvilBlockEntity.OUTPUT_SLOT_NUMBER; i++) {
			if (!recipeItems.get(i).test(container.getItem(i))) {
				return false;
			}
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
		return DivineAnvilRecipeSerializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return DivineAnvilRecipeType.INSTANCE;
	}

	public static class DivineAnvilRecipeType implements RecipeType<DivineAnvilRecipe> {
		private DivineAnvilRecipeType() {}
		
		public static final DivineAnvilRecipeType INSTANCE = new DivineAnvilRecipeType();
		public static final String ID = "divine_anvil_recipes";
	}
	
	public static class DivineAnvilRecipeSerializer implements RecipeSerializer<DivineAnvilRecipe> {
        public static final DivineAnvilRecipeSerializer INSTANCE = new DivineAnvilRecipeSerializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Antiquita.MOD_ID, DivineAnvilRecipeType.ID);

        @Override
        public DivineAnvilRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(DivineAnvilBlockEntity.OUTPUT_SLOT_NUMBER, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new DivineAnvilRecipe(id, output, inputs);
        }

        @Override
        public DivineAnvilRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new DivineAnvilRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, DivineAnvilRecipe recipe) {
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
            return DivineAnvilRecipeSerializer.castClass(RecipeSerializer.class);
        }

        @SuppressWarnings("unchecked")
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
	}
}
