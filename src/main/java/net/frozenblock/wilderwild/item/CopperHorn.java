package net.frozenblock.wilderwild.item;

import java.util.Iterator;
import java.util.Optional;
import net.frozenblock.lib.sound.api.FrozenSoundPackets;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.frozenblock.wilderwild.registry.RegisterItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class CopperHorn extends InstrumentItem {
    private static final String INSTRUMENT_KEY = "instrument";
    private final TagKey<Instrument> instrumentTag;

	public CopperHorn(Properties settings, TagKey<Instrument> instrumentTag) {
        super(settings, instrumentTag);
        this.instrumentTag = instrumentTag;
	}

    public static ItemStack getStackForInstrument(Item item, Holder<Instrument> instrument) {
        ItemStack itemStack = new ItemStack(item);
        setInstrument(itemStack, instrument);
        return itemStack;
    }

    private static void setInstrument(ItemStack stack, Holder<Instrument> instrument) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        nbtCompound.putString(
                INSTRUMENT_KEY, (instrument.unwrapKey().orElseThrow(() -> new IllegalStateException("Invalid instrument"))).location().toString()
        );
    }

    @Override
    public Optional<? extends Holder<Instrument>> getInstrument(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTag();
        if (nbtCompound != null) {
            ResourceLocation identifier = ResourceLocation.tryParse(nbtCompound.getString(INSTRUMENT_KEY));
            if (identifier != null) {
                return BuiltInRegistries.INSTRUMENT.getHolder(ResourceKey.create(Registries.INSTRUMENT, identifier));
            }
        }

        Iterator<Holder<Instrument>> iterator = BuiltInRegistries.INSTRUMENT.getTagOrEmpty(this.instrumentTag).iterator();
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
    }

    @Override
	@NotNull
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player user, @NotNull InteractionHand usedHand) {
        WilderSharedConstants.log(user, "Used Copper Horn", WilderSharedConstants.DEV_LOGGING);
        ItemStack itemStack = user.getItemInHand(usedHand);
        Optional<? extends Holder<Instrument>> optional = this.getInstrument(itemStack);
        if (optional.isPresent()) {
            var instrumentHolder = optional.get();
            var instrument = instrumentHolder.value();
            user.startUsingItem(usedHand);
            playSound(instrument, user, level);
            return InteractionResultHolder.consume(itemStack);
        } else {
            return InteractionResultHolder.fail(itemStack);
        }
    }

    private static void playSound(Instrument instrument, Player user, Level level, Holder<Instrument> instrumentHolder) {
        SoundEvent soundEvent = instrument.soundEvent().value();
        float range = instrument.range() / 16.0F;
        int note = (int) ((-user.getXRot() + 90) / 7.5);

        if (!level.isClientSide) {
            float soundPitch = !user.isShiftKeyDown() ?
                    (float) Math.pow(2.0D, (note - 12.0F) / 12.0D) :
                    (float) Math.pow(2.0D, 0.01111F * -user.getXRot());
            FrozenSoundPackets.createMovingRestrictionLoopingSound(level, user, soundEvent, SoundSource.RECORDS, range, soundPitch, WilderSharedConstants.id("instrument"));
        }
        level.gameEvent(GameEvent.INSTRUMENT_PLAY, user.position(), GameEvent.Context.of(user));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        Optional<? extends Holder<Instrument>> optional = this.getInstrument(stack);
        return optional.map(instrumentRegistryEntry -> instrumentRegistryEntry.value().useDuration()).orElse(0);
    }

    @Override
    public UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.TOOT_HORN;
    }
}
