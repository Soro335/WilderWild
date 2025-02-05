/*
 * Copyright 2023-2025 FrozenBlock
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

package net.frozenblock.wilderwild.worldgen.impl.features;

import com.mojang.serialization.Codec;
import net.frozenblock.lib.worldgen.feature.api.FrozenLibFeatureUtils;
import net.frozenblock.wilderwild.block.CattailBlock;
import net.frozenblock.wilderwild.registry.WWBlocks;
import net.frozenblock.wilderwild.worldgen.impl.features.config.CattailFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jetbrains.annotations.NotNull;

public class CattailFeature extends Feature<CattailFeatureConfig> {

	public CattailFeature(@NotNull Codec<CattailFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(@NotNull FeaturePlaceContext<CattailFeatureConfig> context) {
		boolean generated = false;
		RandomSource random = context.random();
		WorldGenLevel level = context.level();
		BlockPos blockPos = context.origin();
		CattailFeatureConfig config = context.config();
		int posX = blockPos.getX();
		int posZ = blockPos.getZ();
		int maxHeight = level.getMaxY() - 1;
		BlockPos.MutableBlockPos bottomBlockPos = blockPos.mutable();
		BlockPos.MutableBlockPos topBlockPos = blockPos.mutable();
		BlockState topPlaceState = WWBlocks.CATTAIL.defaultBlockState().setValue(CattailBlock.HALF, DoubleBlockHalf.UPPER);
		int placementAttempts = config.placementAttempts().sample(random);
		boolean waterPlacement = config.onlyPlaceInWater();
		TagKey<Block> placeableBlocks = config.canBePlacedOn();

		for (int l = 0; l < placementAttempts; l++) {
			int randomX = config.width().sample(random);
			int randomZ = config.width().sample(random);
			int newX = posX + randomX;
			int newZ = posZ + randomZ;
			int oceanFloorY = level.getHeight(Types.OCEAN_FLOOR, newX, newZ);
			if (oceanFloorY < maxHeight - 1) {
				bottomBlockPos.set(newX, oceanFloorY, newZ);
				BlockState bottomState = level.getBlockState(bottomBlockPos);
				boolean bottomStateIsWater = bottomState.is(Blocks.WATER);
				BlockState bottomPlaceState = WWBlocks.CATTAIL.defaultBlockState();
				topBlockPos.set(bottomBlockPos).move(Direction.UP);
				BlockState topState = level.getBlockState(topBlockPos);
				if ((bottomState.isAir() || (waterPlacement && bottomStateIsWater))
					&& topState.isAir()
					&& bottomPlaceState.canSurvive(level, bottomBlockPos)
					&& (!waterPlacement || (bottomStateIsWater || FrozenLibFeatureUtils.isWaterNearby(level, bottomBlockPos, 2)))
					&& level.getBlockState(bottomBlockPos.move(Direction.DOWN)).is(placeableBlocks)
				) {
					bottomPlaceState = bottomPlaceState.setValue(CattailBlock.WATERLOGGED, bottomStateIsWater);
					level.setBlock(bottomBlockPos.move(Direction.UP), bottomPlaceState, Block.UPDATE_CLIENTS);
					if (topPlaceState.canSurvive(level, topBlockPos)) {
						level.setBlock(topBlockPos, topPlaceState, Block.UPDATE_CLIENTS);
					}
					generated = true;
				}
			}
		}
		return generated;
	}
}
