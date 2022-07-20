package net.frozenblock.wilderwild.entity.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.frozenblock.wilderwild.entity.Firefly;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class FireflyBrain {

    public FireflyBrain() {
    }

    public static Brain<?> create(Brain<Firefly> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void addCoreActivities(Brain<Firefly> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(new Swim(0.8F), new AnimalPanic(2.5F), new LookAtTargetSink(45, 90), new MoveToTargetSink()));
    }

    private static void addIdleActivities(Brain<Firefly> brain) {
        brain.addActivityWithConditions(Activity.IDLE, ImmutableList.of(/*Pair.of(1, new WalkTowardsLookTargetTask<>(FireflyBrain::getHidingPlaceLookTarget, 1, 16, 1.0F)), */Pair.of(2, new StayCloseToTarget<>(FireflyBrain::getLookTarget, 7, 16, 1.0F)), Pair.of(3, new RunSometimes<>(new SetEntityLookTarget((firefly) -> true, 6.0F), UniformInt.of(30, 60))), Pair.of(4, new RunOne<>(ImmutableList.of(Pair.of(new FlyingRandomStroll(1.0F), 2), Pair.of(new SetWalkTargetFromLookTarget(1.0F, 3), 2), Pair.of(new DoNothing(30, 60), 1))))), ImmutableSet.of());
    }

    public static void updateActivities(Firefly firefly) {
        firefly.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.IDLE));
    }

    public static BlockPos getHome(Firefly firefly) {
        Optional<GlobalPos> optional = firefly.getBrain().getMemory(MemoryModuleType.HOME);
        return optional.map(GlobalPos::pos).orElse(null);
    }

    public static BlockPos getHidingPlace(Firefly firefly) {
        Optional<GlobalPos> optional = firefly.getBrain().getMemory(MemoryModuleType.HIDING_PLACE);
        return optional.map(GlobalPos::pos).orElse(null);
    }

    public static boolean isInHomeDimension(Firefly firefly) {
        Optional<GlobalPos> optional = firefly.getBrain().getMemory(MemoryModuleType.HOME);
        return optional.filter(globalPos -> globalPos.dimension() == firefly.level.dimension()).isPresent();
    }

    /*public static boolean isInHidingPlaceDimension(Firefly firefly) {
        Optional<GlobalPos> optional = firefly.getBrain().getOptionalMemory(MemoryModuleType.HIDING_PLACE);
        return optional.filter(globalPos -> globalPos.getDimension() == firefly.world.getRegistryKey()).isPresent();
    }*/

    public static void rememberHome(LivingEntity firefly, BlockPos pos) {
        Brain<?> brain = firefly.getBrain();
        GlobalPos globalPos = GlobalPos.of(firefly.getLevel().dimension(), pos);
        brain.setMemory(MemoryModuleType.HOME, globalPos);
    }

    /*public static void rememberHidingPlace(LivingEntity firefly, BlockPos pos) {
        Brain<?> brain = firefly.getBrain();
        GlobalPos globalPos = GlobalPos.create(firefly.getWorld().getRegistryKey(), pos);
        brain.remember(MemoryModuleType.HIDING_PLACE, globalPos);
    }*/

    /*private static boolean shouldGoToHidingPlace(LivingEntity firefly, GlobalPos pos) {
        World world = firefly.getWorld();
        return ((Firefly) firefly).hasHidingPlace && world.getRegistryKey() == pos.getDimension() && world.isDay();
    }*/

    private static boolean shouldGoTowardsHome(LivingEntity firefly, GlobalPos pos) {
        Level world = firefly.getLevel();
        return ((Firefly) firefly).hasHome && world.dimension() == pos.dimension() && !((Firefly) firefly).shouldHide();
    }

    /*private static Optional<LookTarget> getHidingPlaceLookTarget(LivingEntity firefly) {
        Brain<?> brain = firefly.getBrain();
        Optional<GlobalPos> hiding = brain.getOptionalMemory(MemoryModuleType.HIDING_PLACE);
        if (hiding.isPresent()) {
            GlobalPos hidingPos = hiding.get();
            if (shouldGoToHidingPlace(firefly, hidingPos)) {
                return Optional.of(new BlockPosLookTarget(hidingPos.getPos()));
            }
        }
        return Optional.empty();
    }*/

    private static Optional<PositionTracker> getLookTarget(LivingEntity firefly) {
        Brain<?> brain = firefly.getBrain();
        Optional<GlobalPos> home = brain.getMemory(MemoryModuleType.HOME);
        if (home.isPresent()) {
            GlobalPos globalPos = home.get();
            if (shouldGoTowardsHome(firefly, globalPos)) {
                return Optional.of(new BlockPosTracker(randomPosAround(globalPos.pos(), firefly.level)));
            }
        }

        return Optional.empty();
    }

    private static BlockPos randomPosAround(BlockPos pos, Level world) {
        return pos.offset(world.random.nextIntBetweenInclusive(-7, 7), world.random.nextIntBetweenInclusive(-7, 7), world.random.nextIntBetweenInclusive(-7, 7));
    }
}
