package io.github.phantamanta44.tmemes.client;

import io.github.phantamanta44.tmemes.ElectricToolRegistry;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.client.CustomFontColor;

import java.awt.Color;
import java.util.Objects;

public class MemeEnergyTooltipHandler {

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        if (ElectricToolRegistry.isElectric(event.getItemStack())) {
            IEnergyStorage energy = Objects.requireNonNull(
                    event.getItemStack().getCapability(CapabilityEnergy.ENERGY, EnumFacing.NORTH));
            int stored = energy.getEnergyStored();
            int max = energy.getMaxEnergyStored();
            event.getToolTip().add(1, String.format("%s%,d / %,d FE",
                    CustomFontColor.encodeColor(Color.HSBtoRGB(0.33F * stored / max, 1F, 0.67F)), stored, max));
        }
    }

}
