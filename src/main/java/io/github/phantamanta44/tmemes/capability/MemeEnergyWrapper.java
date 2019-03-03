package io.github.phantamanta44.tmemes.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.Objects;

public class MemeEnergyWrapper implements IEnergyStorage {

    private final ItemStack stack;

    public MemeEnergyWrapper(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        NBTTagCompound tag = Objects.requireNonNull(stack.getTagCompound());
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
        return Objects.requireNonNull(stack.getTagCompound()).getInteger("memeEnergy");
    }

    @Override
    public int getMaxEnergyStored() {
        return Objects.requireNonNull(stack.getTagCompound()).getInteger("memeEnergyCapacity");
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
