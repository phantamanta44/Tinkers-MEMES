package io.github.phantamanta44.tmemes.trait;

import io.github.phantamanta44.tmemes.capability.MemeEnergyWrapper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.tools.modifiers.ModMendingMoss;

import java.util.Objects;

public class TraitDirectedFluxField extends ModifierTrait {

    public TraitDirectedFluxField() {
        super("meme-electric-redirect", 0x9a104f);
    }

    @Override
    public boolean canApplyCustom(ItemStack stack) {
        return stack.hasCapability(CapabilityEnergy.ENERGY, null)
                && !(stack.getCapability(CapabilityEnergy.ENERGY, null) instanceof MemeEnergyWrapper)
                && super.canApplyCustom(stack);
    }

    @Override
    public boolean canApplyTogether(IToolMod o) {
        return !(o instanceof ModMendingMoss);
    }

    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        if (entity.getEntityWorld().isRemote)
            return 0;
        if (newDamage > 0 && tool.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage energy = Objects.requireNonNull(tool.getCapability(CapabilityEnergy.ENERGY, null));
            int cost = newDamage * TraitElectromechanical.ENERGY_PER_WORK;
            return (int)Math.ceil(newDamage * (1 - (float)energy.extractEnergy(cost, false) / cost));
        }
        return newDamage;
    }

    @Override
    public int getPriority() {
        return 1;
    }

}
