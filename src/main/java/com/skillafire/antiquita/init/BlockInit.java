package com.skillafire.antiquita.init;

import java.util.function.Function;

import com.google.common.base.Supplier;
import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.block.DeepslateDivineFireOre;
import com.skillafire.antiquita.block.DeepslateOrichalcumOre;
import com.skillafire.antiquita.block.DivineFireOre;
import com.skillafire.antiquita.block.OrichalcumOre;
import com.skillafire.antiquita.blockentity.DivineFurnace;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Antiquita.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = ItemInit.ITEMS;
	
	// Blocks
	public static final RegistryObject<Block> ORICHALCUM_ORE = registerWithBlockItem("orichalcum_ore", 
			() -> new OrichalcumOre(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Block> DEEPSLATE_ORICHALCUM_ORE = registerWithBlockItem("deepslate_orichalcum_ore", 
			() -> new DeepslateOrichalcumOre(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Block> ORICHALCUM_BLOCK = registerWithBlockItem("orichalcum_block", 
			() -> new Block(Properties.copy(Blocks.GOLD_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	
	
	public static final RegistryObject<Block> DIVINE_FIRE_ORE = registerWithBlockItem("divine_fire_ore", 
			() -> new DivineFireOre(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Block> DEEPSLATE_DIVINE_FIRE_ORE = registerWithBlockItem("deepslate_divine_fire_ore", 
			() -> new DeepslateDivineFireOre(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	
	public static final RegistryObject<Block> DIVINE_FURNACE = registerWithBlockItem("divine_furnace", 
			() -> new DivineFurnace(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	/*public static final RegistryObject<Block> KAMACITE_ORE = registerWithBlockItem("kamacite_ore", 
			() -> new KamaciteOre(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Block> TERRALITE_BLOCK = registerWithBlockItem("terralite_block", 
			() -> new Block(Properties.copy(Blocks.GOLD_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Block> TERRALITE_ORE = registerWithBlockItem("terralite_ore", 
			() -> new TerraliteOre(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));*/
	
	/*public static final RegistryObject<Block> DEEPSLATE_TERRALITE_ORE = registerWithBlockItem("deepslate_terralite_ore", 
			() -> new DeepslateTerraliteOre(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Block> SKY_TREE_LEAVES = registerWithBlockItem("sky_tree_leaves", 
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).emissiveRendering((state, getter, pos) -> true)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}
				
				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			},
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));*/
			
	// Tree
	/*public static final RegistryObject<Block> SKY_TREE_LOG = registerWithBlockItem("sky_tree_log", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<Block> SKY_TREE_WOOD = registerWithBlockItem("sky_tree_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<Block> STRIPPED_SKY_TREE_LOG = registerWithBlockItem("stripped_sky_tree_log", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	public static final RegistryObject<Block> STRIPPED_SKY_TREE_WOOD = registerWithBlockItem("stripped_sky_tree_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Block> SKY_TREE_PLANKS = registerWithBlockItem("sky_tree_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            }, 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));*/
	
	// Saplings
	/*public static final RegistryObject<Block> SKY_TREE_SAPLING = registerWithBlockItem("sky_tree_sapling", 
			() -> new SaplingBlock(new SkyTreeGrower() , BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));*/
	
	// Entities
	/*public static final RegistryObject<Block> WORKTABLE = registerWithBlockItem("worktable", 
			() -> new Worktable(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Block> SKY_FURNACE = registerWithBlockItem("sky_furnace", 
			() -> new SkyFurnace(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));*/
	
	// Flowers
	/*public static final RegistryObject<Block> STARGONIA_FLOWER = registerWithBlockItem("stargonia_flower", 
			() -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 5, BlockBehaviour.Properties.copy(Blocks.DANDELION).noOcclusion()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Antiquita.ANTIQUITA_TAB)));
	
	public static final RegistryObject<Block> POTTED_STARGONIA_FLOWER = registerWithoutBlockItem("potted_stargonia_flower", 
			() -> new PottedStargoniaFlower(null, BlockInit.STARGONIA_FLOWER, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));*/
	
	// Register methods
	private static <T extends Block> RegistryObject<T> registerWithoutBlockItem(final String name, final Supplier<? extends T> block) {
		return registerBlock(name, block);
	}

	private static <T extends Block> RegistryObject<T> registerWithBlockItem(final String name, final Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> obj = registerBlock(name, block);
		ITEMS.register(name, item.apply(obj));
		
		return obj;
	}
	
	private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> block) {
		return BLOCKS.register(name, block);
	}
	
	public static void register(IEventBus bus) {
		BLOCKS.register(bus);
	}
}
