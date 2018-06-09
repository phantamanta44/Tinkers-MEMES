package io.github.phantamanta44.tmemes.trait;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.tools.modifiers.ModMendingMoss;

public class TraitElectromechanical extends ModifierTrait {

    private static final int ENERGY_PER_WORK = 160;
    private static final int ENERGY_CAPACITY = 80000;

    public TraitElectromechanical() {
        super("meme-electric", 0x9a1610, 5, 0);
    }

    @Override
    public boolean canApplyTogether(IToolMod o) {
        return !(o instanceof ModMendingMoss);
    }

    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        if (newDamage > 0) {
            int energy = tool.getTagCompound().getInteger("memeEnergy"), cost = newDamage * ENERGY_PER_WORK;
            if (energy >= cost) {
                tool.getTagCompound().setInteger("memeEnergy", energy - cost);
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
        rootCompound.setInteger("memeEnergyCapacity", ModifierNBT.readTag(modifierTag).level * ENERGY_CAPACITY);
    }

}
