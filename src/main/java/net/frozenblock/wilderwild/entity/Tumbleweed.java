package net.frozenblock.wilderwild.entity;

import net.frozenblock.lib.wind.api.WindManager;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.frozenblock.wilderwild.tag.WilderBiomeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class Tumbleweed extends Mob {
	private static final double windMultiplier = 1.4;
	private static final double windClamp = 0.17;

	public float pitch;
	public float prevPitch;
	public float roll;
	public float prevRoll;

	public Tumbleweed(EntityType<Tumbleweed> entityType, Level level) {
		super(entityType, level);
		this.blocksBuilding = true;
	}

	public static boolean canSpawn(EntityType<Tumbleweed> type, ServerLevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
		return level.getBrightness(LightLayer.SKY, pos) > 7 && random.nextInt(0, 15) == 12;
	}

	public static AttributeSupplier.Builder addAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1D);
	}

	@Override
	public void tick() {
		super.tick();
		double multiplier = this.level.getBrightness(LightLayer.SKY, this.blockPosition()) * 0.0667;
		double windX = Mth.clamp(WindManager.windX * windMultiplier, -windClamp, windClamp);
		double windZ = Mth.clamp(WindManager.windZ * windMultiplier, -windClamp, windClamp);
		Vec3 deltaMovement = this.getDeltaMovement();
		deltaMovement = deltaMovement.add((windX * 0.2) * multiplier, 0, (windZ * 0.2) * multiplier);
		deltaMovement = new Vec3(deltaMovement.x, deltaMovement.y * 0.9, deltaMovement.z);
		this.setDeltaMovement(deltaMovement);
		Vec3 deltaPos = this.getPosition(1).subtract(this.getPosition(0));
		this.prevPitch = this.pitch;
		this.prevRoll = this.roll;
		this.roll += deltaPos.x * 35;
		this.pitch += deltaPos.z * 35;
		if (deltaPos.y <= 0 && this.isOnGround()) {
			this.setDeltaMovement(deltaMovement.add(0, Math.min(0.5, ((deltaPos.horizontalDistance() * 1.1))) * multiplier, 0));
		}
		if (this.wasTouchingWater || this.wasOnFire) {
			this.spawnBreakParticles();
			this.remove(RemovalReason.KILLED);
		}
	}

	@Override
	protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
		return SoundEvents.MANGROVE_ROOTS_BREAK;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.MANGROVE_ROOTS_BREAK;
	}

	@Override
	public boolean causeFallDamage(float fallDistance, float multiplier, @NotNull DamageSource source) {
		return false;
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag compound) {
		super.readAdditionalSaveData(compound);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag compound) {
		super.addAdditionalSaveData(compound);
	}

	@Override
	public void die(@NotNull DamageSource damageSource) {
		this.spawnBreakParticles();
		this.remove(RemovalReason.KILLED);
	}

	public void spawnBreakParticles() {
		if (this.level instanceof ServerLevel level) {
			level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RegisterBlocks.TUMBLEWEED)), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, this.getBbWidth() / 4.0F, this.getBbHeight() / 4.0F, this.getBbWidth() / 4.0F, 0.05D);
		}
	}


	@Override
	public Iterable<ItemStack> getArmorSlots() {
		return NonNullList.withSize(1, ItemStack.EMPTY);
	}

	@Override
	public ItemStack getItemBySlot(@NotNull EquipmentSlot slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public void setItemSlot(@NotNull EquipmentSlot slot, @NotNull ItemStack stack) {

	}

	@Override
	public HumanoidArm getMainArm() {
		return HumanoidArm.LEFT;
	}
}
