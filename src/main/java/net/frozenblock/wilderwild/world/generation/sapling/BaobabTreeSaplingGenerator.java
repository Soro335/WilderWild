/*
 * Copyright 2022-2023 FrozenBlock
 * This file is part of Wilder Wild.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, see <https://www.gnu.org/licenses/>.
 */

package net.frozenblock.wilderwild.world.generation.sapling;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public abstract class BaobabTreeSaplingGenerator extends AbstractMegaTreeGrower {
    public BaobabTreeSaplingGenerator() {
    }

	/**
	 * This method grows a Baobab tree in the specified position
	 *
	 * @param level the server level object
	 * @param chunkGenerator the chunk generator
	 * @param pos the block position
	 * @param state the current block state
	 * @param random the random source object
	 * @return true if the tree was successfully grown, false otherwise
	 */
	public boolean growTree(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random) {
		// Loop through the x and z position offsets
		for (int x = 0; x >= -4; --x) {
			for (int z = 0; z >= -4; --z) {
				// If the Baobab tree can be generated in the current position
				if (canGenerateBaobabTree(state, level, pos, x, z)) {
					// Attempt to generate the Baobab tree
					return this.generateBaobabTree(level, chunkGenerator, pos, state, random, x, z);
				}
			}
		}

		// If the Baobab tree cannot be grown, call the parent's growTree method
		return super.growTree(level, chunkGenerator, pos, state, random);
	}

	/**
	 * This abstract method returns the holder for the Baobab tree feature
	 *
	 * @param random the random source object
	 * @return the holder for the Baobab tree feature or null if not found
	 */
	@Nullable
	protected abstract Holder<? extends ConfiguredFeature<?, ?>> getBaobabTreeFeature(RandomSource random);


	/**
	 * Overrides the parent method and returns null as this feature is not applicable for the current scenario.
	 *
	 * @param random the random source object
	 * @return null as this feature is not applicable
	 */
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
		return null;
	}

	/**
	 * Generates a Baobab tree.
	 *
	 * @param level The current server level.
	 * @param chunkGenerator The chunk generator for the world.
	 * @param pos The position for the tree to be generated.
	 * @param state The state of the block.
	 * @param random The random source.
	 * @param xPos The x-coordinate position.
	 * @param zPos The z-coordinate position.
	 * @return true if the tree was successfully generated, false otherwise.
	 */
	public boolean generateBaobabTree(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random, int xPos, int zPos) {
		// Get the holder for the Baobab tree feature
		Holder<? extends ConfiguredFeature<?, ?>> holder = this.getBaobabTreeFeature(random);

		// If the holder is null, return false
		if (holder == null) {
			return false;
		} else {
			// Get the configured feature from the holder
			ConfiguredFeature<?, ?> configuredFeature = holder.value();

			// Set the block state to air
			BlockState blockState = Blocks.AIR.defaultBlockState();

			// Clear the area for the tree
			for (var x = xPos; x <= xPos + 3; ++x) {
				for (var z = zPos; z <= zPos + 3; ++z) {
					var nutPos = pos.offset(x, 0, z);
					level.setBlock(nutPos, blockState, 3);
					level.getChunkSource().blockChanged(nutPos);
				}
			}

			// Try to place the tree in the world
			if (configuredFeature.place(level, chunkGenerator, random, pos.offset(xPos, 0, zPos))) {
				return true;
			} else {
				// If the tree could not be placed, clear the area again
				for (var x = xPos; x <= xPos + 3; ++x) {
					for (var z = zPos; z <= zPos + 3; ++z) {
						var nutPos = pos.offset(x, 0, z);
						level.setBlock(nutPos, blockState, 3);
						level.getChunkSource().blockChanged(nutPos);
					}
				}
				return false;
			}
		}
	}

	/**
	 * This method checks if a Baobab tree can be generated in the specified position
	 *
	 * @param state the current block state
	 * @param level the block getter object
	 * @param pos the block position
	 * @param xPos the x position offset
	 * @param zPos the z position offset
	 * @return true if the Baobab tree can be generated, false otherwise
	 */
	public static boolean canGenerateBaobabTree(BlockState state, BlockGetter level, BlockPos pos, int xPos, int zPos) {
		// Get the current block
		Block block = state.getBlock();
		// Initialize a flag to indicate if the tree can be generated
		boolean canGenerate = true;
		// Loop through the x and z position offsets
		for (var x = xPos; x <= xPos + 3; ++x) {
			for (var z = zPos; z <= zPos + 3; ++z) {
				// If the block state at the current offset is not equal to the current block
				if (!level.getBlockState(pos.offset(x, 0, z)).is(block))
					// Set the flag to false
					canGenerate = false;
			}
		}
		// Return the result of the flag
		return canGenerate;
	}
}
