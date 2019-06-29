package io.github.phantamanta44.tmemes.integration.conarm;

import c4.conarm.lib.modifiers.ArmorModifierTrait;
import io.github.phantamanta44.tmemes.trait.TraitElectromechanical;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.tools.modifiers.ModMendingMoss;

import java.util.Objects;

public class ArmourTraitElectromechanical extends ArmorModifierTrait {

    public ArmourTraitElectromechanical() {
        super("meme-electric", 0x9a1610, 5, 0);
    }

    @Override
    public boolean canApplyTogether(IToolMod o) {
        return !(o instanceof ModMendingMoss);
    }

    @Override
    public int onArmorDamage(ItemStack armour, DamageSource source, int damage, int newDamage, EntityPlayer player, int slot) {
        if (player.getEntityWorld().isRemote)
            return 0;
        if (newDamage > 0) {
            int energy = Objects.requireNonNull(armour.getTagCompound()).getInteger("memeEnergy");
            int cost = newDamage * 5 * TraitElectromechanical.ENERGY_PER_WORK;
            if (energy >= cost) {
                armour.getTagCompound().setInteger("memeEnergy", energy - cost);
                return 0;
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
        rootCompound.setInteger("memeEnergyCapacity", ModifierNBT.readTag(modifierTag).level * TraitElectromechanical.ENERGY_CAPACITY);
    }

}
