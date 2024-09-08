package net.frozenblock.wilderwild.registry;

import net.frozenblock.lib.item.api.FrozenCreativeTabs;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class WWCreativeInventorySorting {

	public static void init() {
		// BAOBAB (BUILDING BLOCKS)
		addAfterInBuildingBlocks(Items.MANGROVE_BUTTON, WWBlocks.BAOBAB_LOG);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_LOG, WWBlocks.BAOBAB_WOOD);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_LOG, WWBlocks.BAOBAB_WOOD);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_WOOD, WWBlocks.STRIPPED_BAOBAB_LOG);
		addAfterInBuildingBlocks(WWBlocks.STRIPPED_BAOBAB_LOG, WWBlocks.STRIPPED_BAOBAB_WOOD);
		addAfterInBuildingBlocks(WWBlocks.STRIPPED_BAOBAB_WOOD, WWBlocks.BAOBAB_PLANKS);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_PLANKS, WWBlocks.BAOBAB_STAIRS);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_STAIRS, WWBlocks.BAOBAB_SLAB);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_SLAB, WWBlocks.BAOBAB_FENCE);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_FENCE, WWBlocks.BAOBAB_FENCE_GATE);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_FENCE_GATE, WWBlocks.BAOBAB_DOOR);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_DOOR, WWBlocks.BAOBAB_TRAPDOOR);
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_TRAPDOOR, WWBlocks.BAOBAB_PRESSURE_PLATE);
		// BAOBAB (NATURAL BLOCKS)
		addAfterInNaturalBlocks(Items.MANGROVE_LOG, WWBlocks.BAOBAB_LOG);
		addAfterInNaturalBlocks(Items.MANGROVE_LEAVES, WWBlocks.BAOBAB_LEAVES);
		addAfterInNaturalBlocks(WWBlocks.BAOBAB_LEAVES, WWBlocks.BAOBAB_LEAF_LITTER);

		// CYPRESS (BUILDING BLOCKS)
		addAfterInBuildingBlocks(WWBlocks.BAOBAB_BUTTON, WWBlocks.CYPRESS_LOG);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_LOG, WWBlocks.CYPRESS_WOOD);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_WOOD, WWBlocks.STRIPPED_CYPRESS_LOG);
		addAfterInBuildingBlocks(WWBlocks.STRIPPED_CYPRESS_LOG, WWBlocks.STRIPPED_CYPRESS_WOOD);
		addAfterInBuildingBlocks(WWBlocks.STRIPPED_CYPRESS_WOOD, WWBlocks.CYPRESS_PLANKS);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_PLANKS, WWBlocks.CYPRESS_STAIRS);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_STAIRS, WWBlocks.CYPRESS_SLAB);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_SLAB, WWBlocks.CYPRESS_FENCE);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_FENCE, WWBlocks.CYPRESS_FENCE_GATE);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_FENCE_GATE, WWBlocks.CYPRESS_DOOR);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_DOOR, WWBlocks.CYPRESS_TRAPDOOR);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_TRAPDOOR, WWBlocks.CYPRESS_PRESSURE_PLATE);
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_PRESSURE_PLATE, WWBlocks.CYPRESS_BUTTON);
		// CYPRESS (NATURAL_BLOCKS)
		addAfterInNaturalBlocks(WWBlocks.BAOBAB_LOG, WWBlocks.CYPRESS_LOG);
		addAfterInNaturalBlocks(WWBlocks.BAOBAB_LEAF_LITTER, WWBlocks.CYPRESS_LEAVES);
		addAfterInNaturalBlocks(WWBlocks.CYPRESS_LEAVES, WWBlocks.CYPRESS_LEAF_LITTER);
		addAfterInNaturalBlocks(Items.MANGROVE_PROPAGULE, WWBlocks.CYPRESS_SAPLING);

		// PALM (BUILDING BLOCKS)
		addAfterInBuildingBlocks(WWBlocks.CYPRESS_BUTTON, WWBlocks.PALM_LOG);
		addAfterInBuildingBlocks(WWBlocks.PALM_LOG, WWBlocks.PALM_WOOD);
		addAfterInBuildingBlocks(WWBlocks.PALM_WOOD, WWBlocks.STRIPPED_PALM_LOG);
		addAfterInBuildingBlocks(WWBlocks.STRIPPED_PALM_LOG, WWBlocks.STRIPPED_PALM_WOOD);
		addAfterInBuildingBlocks(WWBlocks.STRIPPED_PALM_WOOD, WWBlocks.PALM_PLANKS);
		addAfterInBuildingBlocks(WWBlocks.PALM_PLANKS, WWBlocks.PALM_STAIRS);
		addAfterInBuildingBlocks(WWBlocks.PALM_STAIRS, WWBlocks.PALM_SLAB);
		addAfterInBuildingBlocks(WWBlocks.PALM_SLAB, WWBlocks.PALM_FENCE);
		addAfterInBuildingBlocks(WWBlocks.PALM_FENCE, WWBlocks.PALM_FENCE_GATE);
		addAfterInBuildingBlocks(WWBlocks.PALM_FENCE_GATE, WWBlocks.PALM_DOOR);
		addAfterInBuildingBlocks(WWBlocks.PALM_DOOR, WWBlocks.PALM_TRAPDOOR);
		addAfterInBuildingBlocks(WWBlocks.PALM_TRAPDOOR, WWBlocks.PALM_PRESSURE_PLATE);
		addAfterInBuildingBlocks(WWBlocks.PALM_PRESSURE_PLATE, WWBlocks.PALM_BUTTON);
		// PALM (NATURAL BLOCKS)
		addAfterInNaturalBlocks(WWBlocks.CYPRESS_LOG, WWBlocks.PALM_LOG);
		addAfterInNaturalBlocks(WWBlocks.CYPRESS_LEAF_LITTER, WWBlocks.PALM_FRONDS);
		addAfterInNaturalBlocks(WWBlocks.PALM_FRONDS, WWBlocks.PALM_FROND_LITTER);

		// MAPLE (BUILDING BLOCKS)
		addAfterInBuildingBlocks(Items.CHERRY_BUTTON, WWBlocks.MAPLE_LOG);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_LOG, WWBlocks.MAPLE_WOOD);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_WOOD, WWBlocks.STRIPPED_MAPLE_LOG);
		addAfterInBuildingBlocks(WWBlocks.STRIPPED_MAPLE_LOG, WWBlocks.STRIPPED_MAPLE_WOOD);
		addAfterInBuildingBlocks(WWBlocks.STRIPPED_MAPLE_WOOD, WWBlocks.MAPLE_PLANKS);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_PLANKS, WWBlocks.MAPLE_STAIRS);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_STAIRS, WWBlocks.MAPLE_SLAB);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_SLAB, WWBlocks.MAPLE_FENCE);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_FENCE, WWBlocks.MAPLE_FENCE_GATE);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_FENCE_GATE, WWBlocks.MAPLE_DOOR);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_DOOR, WWBlocks.MAPLE_TRAPDOOR);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_TRAPDOOR, WWBlocks.MAPLE_PRESSURE_PLATE);
		addAfterInBuildingBlocks(WWBlocks.MAPLE_PRESSURE_PLATE, WWBlocks.MAPLE_BUTTON);
		// MAPLE (NATURAL BLOCKS)
		addAfterInNaturalBlocks(Items.CHERRY_LOG, WWBlocks.MAPLE_LOG);
		addAfterInNaturalBlocks(Items.CHERRY_SAPLING, WWBlocks.MAPLE_SAPLING);
		addAfterInNaturalBlocks(WWBlocks.CHERRY_LEAF_LITTER, WWBlocks.YELLOW_MAPLE_LEAVES);
		addAfterInNaturalBlocks(WWBlocks.YELLOW_MAPLE_LEAVES, WWBlocks.YELLOW_MAPLE_LEAF_LITTER);
		addAfterInNaturalBlocks(WWBlocks.YELLOW_MAPLE_LEAF_LITTER, WWBlocks.ORANGE_MAPLE_LEAVES);
		addAfterInNaturalBlocks(WWBlocks.ORANGE_MAPLE_LEAVES, WWBlocks.ORANGE_MAPLE_LEAF_LITTER);
		addAfterInNaturalBlocks(WWBlocks.ORANGE_MAPLE_LEAF_LITTER, WWBlocks.RED_MAPLE_LEAVES);
		addAfterInNaturalBlocks(WWBlocks.RED_MAPLE_LEAVES, WWBlocks.RED_MAPLE_LEAF_LITTER);

		// OTHER LEAF LITTERS
		addAfterInNaturalBlocks(Blocks.OAK_LEAVES, WWBlocks.OAK_LEAF_LITTER);
		addAfterInNaturalBlocks(Blocks.SPRUCE_LEAVES, WWBlocks.SPRUCE_LEAF_LITTER);
		addAfterInNaturalBlocks(Blocks.BIRCH_LEAVES, WWBlocks.BIRCH_LEAF_LITTER);
		addAfterInNaturalBlocks(Blocks.JUNGLE_LEAVES, WWBlocks.JUNGLE_LEAF_LITTER);
		addAfterInNaturalBlocks(Blocks.ACACIA_LEAVES, WWBlocks.ACACIA_LEAF_LITTER);
		addAfterInNaturalBlocks(Blocks.DARK_OAK_LEAVES, WWBlocks.DARK_OAK_LEAF_LITTER);
		addAfterInNaturalBlocks(Blocks.MANGROVE_LEAVES, WWBlocks.MANGROVE_LEAF_LITTER);
		addAfterInNaturalBlocks(Blocks.CHERRY_LEAVES, WWBlocks.CHERRY_LEAF_LITTER);
		addAfterInNaturalBlocks(Blocks.AZALEA_LEAVES, WWBlocks.AZALEA_LEAF_LITTER);
		addAfterInNaturalBlocks(Blocks.FLOWERING_AZALEA_LEAVES, WWBlocks.FLOWERING_AZALEA_LEAF_LITTER);

		// HOLLOWED LOGS
		addAfterInBuildingAndNaturalBlocks(Items.OAK_LOG, WWBlocks.HOLLOWED_OAK_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_OAK_LOG, WWBlocks.STRIPPED_HOLLOWED_OAK_LOG);

		addAfterInBuildingAndNaturalBlocks(Items.SPRUCE_LOG, WWBlocks.HOLLOWED_SPRUCE_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_SPRUCE_LOG, WWBlocks.STRIPPED_HOLLOWED_SPRUCE_LOG);

		addAfterInBuildingAndNaturalBlocks(Items.BIRCH_LOG, WWBlocks.HOLLOWED_BIRCH_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_BIRCH_LOG, WWBlocks.STRIPPED_HOLLOWED_BIRCH_LOG);

		addAfterInBuildingAndNaturalBlocks(Items.JUNGLE_LOG, WWBlocks.HOLLOWED_JUNGLE_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_JUNGLE_LOG, WWBlocks.STRIPPED_HOLLOWED_JUNGLE_LOG);

		addAfterInBuildingAndNaturalBlocks(Items.ACACIA_LOG, WWBlocks.HOLLOWED_ACACIA_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_ACACIA_LOG, WWBlocks.STRIPPED_HOLLOWED_ACACIA_LOG);

		addAfterInBuildingAndNaturalBlocks(Items.DARK_OAK_LOG, WWBlocks.HOLLOWED_DARK_OAK_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_DARK_OAK_LOG, WWBlocks.STRIPPED_HOLLOWED_DARK_OAK_LOG);

		addAfterInBuildingAndNaturalBlocks(Items.CRIMSON_STEM, WWBlocks.HOLLOWED_CRIMSON_STEM);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_CRIMSON_STEM, WWBlocks.STRIPPED_HOLLOWED_CRIMSON_STEM);

		addAfterInBuildingAndNaturalBlocks(Items.WARPED_STEM, WWBlocks.HOLLOWED_WARPED_STEM);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_WARPED_STEM, WWBlocks.STRIPPED_HOLLOWED_WARPED_STEM);

		addAfterInBuildingAndNaturalBlocks(Items.MANGROVE_LOG, WWBlocks.HOLLOWED_MANGROVE_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_MANGROVE_LOG, WWBlocks.STRIPPED_HOLLOWED_MANGROVE_LOG);

		addAfterInBuildingAndNaturalBlocks(Items.CHERRY_LOG, WWBlocks.HOLLOWED_CHERRY_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_CHERRY_LOG, WWBlocks.STRIPPED_HOLLOWED_CHERRY_LOG);

		addBeforeInBuildingBlocks(WWBlocks.BAOBAB_WOOD, WWBlocks.HOLLOWED_BAOBAB_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_BAOBAB_LOG, WWBlocks.STRIPPED_HOLLOWED_BAOBAB_LOG);
		addAfterInNaturalBlocks(WWBlocks.BAOBAB_LOG, WWBlocks.HOLLOWED_BAOBAB_LOG);

		addBeforeInBuildingBlocks(WWBlocks.CYPRESS_WOOD, WWBlocks.HOLLOWED_CYPRESS_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_CYPRESS_LOG, WWBlocks.STRIPPED_HOLLOWED_CYPRESS_LOG);
		addAfterInNaturalBlocks(WWBlocks.CYPRESS_LOG, WWBlocks.HOLLOWED_CYPRESS_LOG);

		addBeforeInBuildingBlocks(WWBlocks.PALM_WOOD, WWBlocks.HOLLOWED_PALM_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_PALM_LOG, WWBlocks.STRIPPED_HOLLOWED_PALM_LOG);
		addAfterInNaturalBlocks(WWBlocks.PALM_LOG, WWBlocks.HOLLOWED_PALM_LOG);

		addBeforeInBuildingBlocks(WWBlocks.MAPLE_WOOD, WWBlocks.HOLLOWED_MAPLE_LOG);
		addAfterInBuildingBlocks(WWBlocks.HOLLOWED_MAPLE_LOG, WWBlocks.STRIPPED_HOLLOWED_MAPLE_LOG);
		addAfterInNaturalBlocks(WWBlocks.MAPLE_LOG, WWBlocks.HOLLOWED_MAPLE_LOG);

		// SMALL FLOWERS
		addAfterInNaturalBlocks(Items.DANDELION, WWBlocks.SEEDING_DANDELION);
		addAfterInNaturalBlocks(Items.CORNFLOWER, WWBlocks.CARNATION);
		addAfterInNaturalBlocks(WWBlocks.CARNATION, WWBlocks.MARIGOLD);
		addBeforeInNaturalBlocks(Items.WITHER_ROSE, WWBlocks.GLORY_OF_THE_SNOW);
		addAfterInNaturalBlocks(WWBlocks.GLORY_OF_THE_SNOW, WWBlocks.ALBA_GLORY_OF_THE_SNOW);
		addAfterInNaturalBlocks(WWBlocks.ALBA_GLORY_OF_THE_SNOW, WWBlocks.VIOLET_BEAUTY_GLORY_OF_THE_SNOW);
		addAfterInNaturalBlocks(WWBlocks.VIOLET_BEAUTY_GLORY_OF_THE_SNOW, WWBlocks.PINK_GIANT_GLORY_OF_THE_SNOW);
		addAfterInNaturalBlocks(WWBlocks.PINK_GIANT_GLORY_OF_THE_SNOW, WWBlocks.BLUE_GIANT_GLORY_OF_THE_SNOW);

		// TALL FLOWERS
		addAfterInNaturalBlocks(Items.PEONY, WWBlocks.DATURA);
		addAfterInNaturalBlocks(WWBlocks.DATURA, WWBlocks.MILKWEED);
		addAfterInNaturalBlocks(WWBlocks.MILKWEED, WWBlocks.CATTAIL);

		// PLANTS
		addAfterInNaturalBlocks(Items.CACTUS, WWBlocks.TUMBLEWEED_PLANT);
		addAfterInNaturalBlocks(WWBlocks.TUMBLEWEED_PLANT, WWBlocks.TUMBLEWEED);
		addAfterInNaturalBlocks(WWBlocks.TUMBLEWEED, WWBlocks.BUSH);

		// SHELF FUNGUS
		addAfterInNaturalBlocks(Items.RED_MUSHROOM, WWBlocks.RED_SHELF_FUNGUS);
		addAfterInNaturalBlocks(Items.RED_MUSHROOM, WWBlocks.BROWN_SHELF_FUNGUS);

		// SPONGE
		addAfterInNaturalBlocks(Items.WET_SPONGE, WWBlocks.SPONGE_BUD);

		// EGGS
		addBeforeInNaturalBlocks(Items.SNIFFER_EGG, WWBlocks.OSTRICH_EGG);

		// MESOGLEA
		addBeforeInNaturalBlocks(Items.SPONGE, WWBlocks.BLUE_PEARLESCENT_MESOGLEA);
		addAfterInNaturalBlocks(WWBlocks.BLUE_PEARLESCENT_MESOGLEA, WWBlocks.PURPLE_PEARLESCENT_MESOGLEA);
		addAfterInNaturalBlocks(WWBlocks.PURPLE_PEARLESCENT_MESOGLEA, WWBlocks.BLUE_MESOGLEA);
		addAfterInNaturalBlocks(WWBlocks.BLUE_MESOGLEA, WWBlocks.PINK_MESOGLEA);
		addAfterInNaturalBlocks(WWBlocks.PINK_MESOGLEA, WWBlocks.RED_MESOGLEA);
		addAfterInNaturalBlocks(WWBlocks.RED_MESOGLEA, WWBlocks.YELLOW_MESOGLEA);
		addAfterInNaturalBlocks(WWBlocks.YELLOW_MESOGLEA, WWBlocks.LIME_MESOGLEA);

		// NEMATOCYST
		addBeforeInNaturalBlocks(Items.SPONGE, WWBlocks.BLUE_PEARLESCENT_NEMATOCYST);
		addAfterInNaturalBlocks(WWBlocks.BLUE_PEARLESCENT_NEMATOCYST, WWBlocks.PURPLE_PEARLESCENT_NEMATOCYST);
		addAfterInNaturalBlocks(WWBlocks.PURPLE_PEARLESCENT_NEMATOCYST, WWBlocks.BLUE_NEMATOCYST);
		addAfterInNaturalBlocks(WWBlocks.BLUE_NEMATOCYST, WWBlocks.PINK_NEMATOCYST);
		addAfterInNaturalBlocks(WWBlocks.PINK_NEMATOCYST, WWBlocks.RED_NEMATOCYST);
		addAfterInNaturalBlocks(WWBlocks.RED_NEMATOCYST, WWBlocks.YELLOW_NEMATOCYST);
		addAfterInNaturalBlocks(WWBlocks.YELLOW_NEMATOCYST, WWBlocks.LIME_NEMATOCYST);

		// MUD BRICKS
		addAfterInBuildingBlocks(Items.MUD_BRICKS, WWBlocks.CRACKED_MUD_BRICKS);
		addAfterInBuildingBlocks(Items.MUD_BRICK_WALL, WWBlocks.CHISELED_MUD_BRICKS);
		addAfterInBuildingBlocks(WWBlocks.CHISELED_MUD_BRICKS, WWBlocks.MOSSY_MUD_BRICKS);
		addAfterInBuildingBlocks(WWBlocks.MOSSY_MUD_BRICKS, WWBlocks.MOSSY_MUD_BRICK_STAIRS);
		addAfterInBuildingBlocks(WWBlocks.MOSSY_MUD_BRICK_STAIRS, WWBlocks.MOSSY_MUD_BRICK_SLAB);
		addAfterInBuildingBlocks(WWBlocks.MOSSY_MUD_BRICK_SLAB, WWBlocks.MOSSY_MUD_BRICK_WALL);

		// SCULK
		addAfterInBuildingBlocks(Items.DEEPSLATE_TILE_WALL, WWBlocks.OSSEOUS_SCULK);
		addAfterInBuildingBlocks(WWBlocks.OSSEOUS_SCULK, WWBlocks.SCULK_STAIRS);
		addAfterInBuildingBlocks(WWBlocks.SCULK_STAIRS, WWBlocks.SCULK_SLAB);
		addAfterInBuildingBlocks(WWBlocks.SCULK_SLAB, WWBlocks.SCULK_WALL);
		addAfterInNaturalBlocks(Items.SCULK, WWBlocks.OSSEOUS_SCULK);
		addAfterInNaturalBlocks(Items.SCULK_SENSOR, WWBlocks.HANGING_TENDRIL);

		// STORAGE
		addAfterInFunctionalBlocks(Items.CHEST, WWBlocks.STONE_CHEST);
		addAfterInNaturalAndFunctionalBlocks(Items.MAGMA_BLOCK, WWBlocks.GEYSER);
		addBeforeInRedstoneBlocks(Items.SCULK_SENSOR, WWBlocks.GEYSER);

		// FUNCTIONAL BLOCK ENTITIES
		addBeforeInNaturalBlocks(Items.BEE_NEST, WWBlocks.TERMITE_MOUND);

		// MISC
		addBeforeInBuildingBlocks(Items.GLASS, WWBlocks.NULL_BLOCK);
	}

	private static void addBeforeInBuildingBlocks(ItemLike comparedItem, ItemLike item) {
		FrozenCreativeTabs.addBefore(comparedItem, item, CreativeModeTabs.BUILDING_BLOCKS);
	}

	private static void addAfterInBuildingBlocks(ItemLike comparedItem, ItemLike item) {
		FrozenCreativeTabs.addAfter(comparedItem, item, CreativeModeTabs.BUILDING_BLOCKS);
	}

	private static void addBeforeInNaturalBlocks(ItemLike comparedItem, ItemLike item) {
		FrozenCreativeTabs.addBefore(comparedItem, item, CreativeModeTabs.NATURAL_BLOCKS);
	}

	private static void addAfterInNaturalBlocks(ItemLike comparedItem, ItemLike item) {
		FrozenCreativeTabs.addAfter(comparedItem, item, CreativeModeTabs.NATURAL_BLOCKS);
	}

	private static void addAfterInBuildingAndNaturalBlocks(ItemLike comparedItem, ItemLike item) {
		FrozenCreativeTabs.addAfter(comparedItem, item, CreativeModeTabs.BUILDING_BLOCKS, CreativeModeTabs.NATURAL_BLOCKS);
	}

	private static void addAfterInNaturalAndFunctionalBlocks(ItemLike comparedItem, ItemLike item) {
		FrozenCreativeTabs.addAfter(comparedItem, item, CreativeModeTabs.NATURAL_BLOCKS, CreativeModeTabs.FUNCTIONAL_BLOCKS);
	}

	private static void addAfterInFunctionalBlocks(ItemLike comparedItem, ItemLike item) {
		FrozenCreativeTabs.addAfter(comparedItem, item, CreativeModeTabs.FUNCTIONAL_BLOCKS);
	}

	private static void addBeforeInRedstoneBlocks(ItemLike comparedItem, ItemLike item) {
		FrozenCreativeTabs.addBefore(comparedItem, item, CreativeModeTabs.REDSTONE_BLOCKS);
	}
}
