/*
 * Copyright 2024 FrozenBlock
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

package net.frozenblock.wilderwild.networking.packet;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.frozenblock.wilderwild.WWConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public record WWScorchingFirePlacePacket(BlockPos pos) implements FabricPacket {
	public static final PacketType<WWScorchingFirePlacePacket> PACKET_TYPE = PacketType.create(
		WWConstants.id("scorching_fire_place"),
		WWScorchingFirePlacePacket::new
	);

	public WWScorchingFirePlacePacket(@NotNull FriendlyByteBuf buf) {
		this(buf.readBlockPos());
	}

	public static void sendToAll(ServerLevel serverLevel, BlockPos pos) {
		for (ServerPlayer player : PlayerLookup.tracking(serverLevel, pos)) {
			ServerPlayNetworking.send(
				player,
				new WWScorchingFirePlacePacket(
					pos
				)
			);
		}
	}

	@Override
	public void write(@NotNull FriendlyByteBuf buf) {
		buf.writeBlockPos(this.pos());
	}

	@Override
	public PacketType<?> getType() {
		return PACKET_TYPE;
	}
}
