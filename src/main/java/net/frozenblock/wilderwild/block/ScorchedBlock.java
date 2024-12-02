/*
 * Copyright 2023-2024 FrozenBlock
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

package net.frozenblock.wilderwild.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import java.util.Optional;
import net.frozenblock.lib.block.api.dripstone.DripstoneUtils;
import net.frozenblock.wilderwild.block.entity.ScorchedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ScorchedBlock extends BaseEntityBlock {
	public static final int TICK_DELAY = 2;
	public static final float RAIN_HYDRATION_CHANCE = 0.75F;
	public static final Map<BlockState, BlockState> SCORCH_MAP = new Object2ObjectOpenHashMap<>();
	public static final Map<BlockState, BlockState> HYDRATE_MAP = new Object2ObjectOpenHashMap<>();
	public static final MapCodec<ScorchedBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
		BlockState.CODEC.fieldOf("previous_state").forGetter((scorchedBlock) -> scorchedBlock.wetState),
		BlockState.CODEC.lenientOptionalFieldOf("scorch_state").forGetter((scorchedBlock) -> scorchedBlock.scorchState),
		Codec.BOOL.fieldOf("brushable").forGetter((scorchedBlock) -> scorchedBlock.canBrush),
		SoundEvent.DIRECT_CODEC.lenientOptionalFieldOf("brush_sound").forGetter((scorchedBlock) -> scorchedBlock.brushSound),
		SoundEvent.DIRECT_CODEC.lenientOptionalFieldOf("brush_completed_sound").forGetter((scorchedBlock) -> scorchedBlock.brushCompletedSound),
		propertiesCodec()
	).apply(instance, ScorchedBlock::new));
	private static final IntegerProperty DUSTED = BlockStateProperties.DUSTED;
	public final boolean canBrush;
	public final BlockState wetState;
	public final Optional<BlockState> scorchState;
	public final Optional<SoundEvent> brushSound;
	public final Optional<SoundEvent> brushCompletedSound;

	public ScorchedBlock(
		@NotNull BlockState wetState,
		@NotNull Optional<BlockState> scorchState,
		boolean canBrush,
		@NotNull Optional<SoundEvent> brushSound,
		@NotNull Optional<SoundEvent> brushCompletedSound,
		@NotNull Properties settings
	) {
		super(settings);
		this.wetState = wetState;
		this.scorchState = scorchState;
		this.canBrush = canBrush;
		this.brushSound = brushSound;
		this.brushCompletedSound = brushCompletedSound;
		this.fillScorchMap(this.wetState, this.defaultBlockState(), this.scorchState.orElse(null));
	}

	public ScorchedBlock(
		@NotNull BlockState wetState,
		BlockState scorchState,
		boolean canBrush,
		SoundEvent brushSound,
		SoundEvent brushCompletedSound,
		@NotNull Properties settings
	) {
		this(wetState, Optional.ofNullable(scorchState), canBrush, Optional.ofNullable(brushSound), Optional.ofNullable(brushCompletedSound), settings);
	}

	public static boolean canScorch(@NotNull BlockState state) {
		return SCORCH_MAP.containsKey(stateWithoutDusting(state));
	}

	public static void scorch(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
		state = stateWithoutDusting(state);
		if (canScorch(state)) {
			level.setBlock(pos, SCORCH_MAP.get(state), UPDATE_ALL);
			level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
		}
	}

	public static boolean canHydrate(@NotNull BlockState state) {
		return HYDRATE_MAP.containsKey(stateWithoutDusting(state));
	}

	public static void hydrate(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
		state = stateWithoutDusting(state);
		if (canHydrate(state)) {
			level.setBlockAndUpdate(pos, HYDRATE_MAP.get(state));
			level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
		}
	}

	@NotNull
	private static BlockState stateWithoutDusting(@NotNull BlockState state) {
		return state.hasProperty(DUSTED) ? state.setValue(DUSTED, 0) : state;
	}

	@NotNull
	@Override
	protected MapCodec<? extends ScorchedBlock> codec() {
		return CODEC;
	}

	public void fillScorchMap(BlockState wetState, BlockState defaultState, @Nullable BlockState defaultStateCracked) {
		SCORCH_MAP.put(wetState, defaultState);
		HYDRATE_MAP.put(defaultState, wetState);
		if (defaultStateCracked != null) {
			SCORCH_MAP.put(defaultState, defaultStateCracked);
			HYDRATE_MAP.put(defaultStateCracked, defaultState);
		}
	}

	@Override
	protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DUSTED);
	}

	@Override
	public void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
		level.scheduleTick(pos, this, TICK_DELAY);
	}

	@Override
	public @NotNull BlockState updateShape(
		@NotNull BlockState state,
		@NotNull Direction direction,
		@NotNull BlockState neighborState,
		@NotNull LevelAccessor level,
		@NotNull BlockPos currentPos,
		@NotNull BlockPos neighborPos
	) {
		level.scheduleTick(currentPos, this, TICK_DELAY);
		return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
	}

	@Override
	public void handlePrecipitation(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Biome.@NotNull Precipitation precipitation) {
		if (precipitation == Biome.Precipitation.RAIN && level.getRandom().nextFloat() < RAIN_HYDRATION_CHANCE) {
			hydrate(state, level, pos);
		}
	}

	@Override
	@Nullable
	public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
		return new ScorchedBlockEntity(blockPos, blockState);
	}

	@Override
	public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		FluidState fluidState = level.getFluidState(pos.above());
		if (fluidState.is(FluidTags.WATER)) {
			hydrate(state, level, pos);
		}
	}

	@Override
	public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		Fluid fluid = DripstoneUtils.getDripstoneFluid(level, pos);
		if (fluid == Fluids.LAVA) {
			if (random.nextBoolean()) {
				scorch(state, level, pos);
			}
		} else if (fluid == Fluids.WATER) {
			hydrate(state, level, pos);
		}
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof ScorchedBlockEntity scorchedBlock) {
			scorchedBlock.checkReset();
		}
	}

	@Override
	@NotNull
	public RenderShape getRenderShape(@NotNull BlockState blockState) {
		return RenderShape.MODEL;
	}
}

