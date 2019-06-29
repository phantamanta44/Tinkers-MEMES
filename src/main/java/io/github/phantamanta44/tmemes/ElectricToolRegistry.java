package io.github.phantamanta44.tmemes;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import slimeknights.tconstruct.library.tools.TinkerToolCore;

import java.util.List;
import java.util.function.Predicate;

public class ElectricToolRegistry {

    private static final List<Predicate<ItemStack>> predicates = Lists.newArrayList(s -> s.getItem() instanceof TinkerToolCore);

    public static void registerPredicate(Predicate<ItemStack> predicate) {
        predicates.add(predicate);
    }

    public static boolean isElectric(ItemStack stack) {
        return stack.hasCapability(CapabilityEnergy.ENERGY, EnumFacing.NORTH) && isPotentiallyElectric(stack);
    }

    public static boolean isPotentiallyElectric(ItemStack stack) {
        return predicates.stream().anyMatch(p -> p.test(stack));
    }

}
