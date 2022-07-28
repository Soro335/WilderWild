package net.frozenblock.wilderwild.misc;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public interface SculkSensorTickInterface {

    void tickServer(ServerWorld world, BlockPos pos, BlockState state);

    int getAge();

    int getAnimTicks();

    int getPrevAnimTicks();

    boolean isActive();

    void setActive(boolean active);

    void setAnimTicks(int i);

}
