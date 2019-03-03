package io.github.phantamanta44.tmemes.capability;

import io.github.phantamanta44.tmemes.MEMES;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.tools.TinkerToolCore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityEventHandler {

    private static final ResourceLocation CAP_SRC = new ResourceLocation(MEMES.MOD_ID, "memeEnergyCapability");

    @SubscribeEvent
    public void modify(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof TinkerToolCore) {
            event.addCapability(CAP_SRC, new MemeCapabilityProvider(event.getObject()));
        }
    }

    private static class MemeCapabilityProvider implements ICapabilityProvider {

        private final ItemStack stack;

        public MemeCapabilityProvider(ItemStack stack) {
            this.stack = stack;
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityEnergy.ENERGY
                    && stack.hasTagCompound() && stack.getTagCompound().hasKey("memeEnergyCapacity");
        }

        @Nullable
        @Override
        @SuppressWarnings("unchecked")
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if (capability == CapabilityEnergy.ENERGY) {
                return (T)new MemeEnergyWrapper(stack);
            }
            return null;
        }

    }

}
