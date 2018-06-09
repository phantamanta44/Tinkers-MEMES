package io.github.phantamanta44.tmemes.capability;

import io.github.phantamanta44.tmemes.MEMES;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.client.CustomFontColor;
import slimeknights.tconstruct.library.tools.TinkerToolCore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;

public class CapabilityEventHandler {

    private static final ResourceLocation CAP_SRC = new ResourceLocation(MEMES.MOD_ID, "memeEnergyCapability");

    @SubscribeEvent
    public void modify(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof TinkerToolCore) {
            event.addCapability(CAP_SRC, new MemeCapabilityProvider(event.getObject()));
        }
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof TinkerToolCore
                && event.getItemStack().hasCapability(CapabilityEnergy.ENERGY, EnumFacing.NORTH)) {
            IEnergyStorage energy = event.getItemStack().getCapability(CapabilityEnergy.ENERGY, EnumFacing.NORTH);
            int stored = energy.getEnergyStored();
            int max = energy.getMaxEnergyStored();
            event.getToolTip().add(1, String.format("%s%,d / %,d FE",
                    CustomFontColor.encodeColor(Color.HSBtoRGB(0.33F * stored / max, 1F, 0.67F)), stored, max));
        }
    }

    private static class MemeCapabilityProvider implements ICapabilityProvider {

        private final ItemStack stack;

        public MemeCapabilityProvider(ItemStack stack) {
            this.stack = stack;
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityEnergy.ENERGY
                    && stack.hasTagCompound()
                    && stack.getTagCompound().hasKey("memeEnergyCapacity");
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
