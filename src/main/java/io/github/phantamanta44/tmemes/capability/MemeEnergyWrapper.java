package io.github.phantamanta44.tmemes.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.IEnergyStorage;

public class MemeEnergyWrapper implements IEnergyStorage {

    private final ItemStack stack;

    public MemeEnergyWrapper(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        NBTTagCompound tag = stack.getTagCompound();
        int stored = tag.getInteger("memeEnergy");
        int toTransfer = Math.min(maxReceive, tag.getInteger("memeEnergyCapacity") - stored);
        if (!simulate)
            tag.setInteger("memeEnergy", stored + toTransfer);
        return toTransfer;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return stack.getTagCompound().getInteger("memeEnergy");
    }

    @Override
    public int getMaxEnergyStored() {
        return stack.getTagCompound().getInteger("memeEnergyCapacity");
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

}
