package io.github.phantamanta44.tmemes.trait;

import io.github.phantamanta44.tmemes.MemeConfig;
import io.github.phantamanta44.tmemes.capability.MemeEnergyWrapper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.CapabilityEnergy;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.tools.modifiers.ModMendingMoss;

import java.util.Objects;

public class TraitElectromechanical extends ModifierTrait {

    public TraitElectromechanical() {
        super("meme-electric", 0x9a1610, MemeConfig.electromechanical.maxLevel, 0);
    }

    @Override
    public boolean canApplyCustom(ItemStack stack) {
        return (!stack.hasCapability(CapabilityEnergy.ENERGY, null)
                || stack.getCapability(CapabilityEnergy.ENERGY, null) instanceof MemeEnergyWrapper)
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
        if (newDamage > 0) {
            int level = ModifierNBT.readTag(TinkerUtil.getModifierTag(tool, identifier)).level;
            double chance = MemeConfig.electromechanical.baseProcChance
                    + (level - 1) * MemeConfig.electromechanical.additionalProcChance;
            if (chance > 0 && Math.random() <= chance) {
                int energy = Objects.requireNonNull(tool.getTagCompound()).getInteger("memeEnergy");
                int cost = newDamage * MemeConfig.electromechanical.energyUse;
                if (energy >= cost) {
                    tool.getTagCompound().setInteger("memeEnergy", energy - cost);
                    return 0;
                }
            }
        }
        return newDamage;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        super.applyEffect(rootCompound, modifierTag);
        if (!rootCompound.hasKey("memeEnergy")) {
            rootCompound.setInteger("memeEnergy", 0);
        }
        rootCompound.setInteger("memeEnergyCapacity",
                ModifierNBT.readTag(modifierTag).level * MemeConfig.electromechanical.energyBufferPerLevel);
    }

}
