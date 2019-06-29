package io.github.phantamanta44.tmemes.integration.conarm;

import c4.conarm.lib.modifiers.ArmorModifierTrait;
import c4.conarm.lib.utils.RecipeMatchHolder;
import io.github.phantamanta44.tmemes.item.MemeItems;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class MemeArmourTraits {

    public static ArmourTraitElectromechanical ELECTROMECHANICAL;
    public static final List<ArmorModifierTrait> ARMOUR_MODS = new ArrayList<>();

    public static void init() {
        ARMOUR_MODS.add(ELECTROMECHANICAL = new ArmourTraitElectromechanical());
        RecipeMatchHolder.addItem(ELECTROMECHANICAL, new ItemStack(MemeItems.ELECTRIC_UPGRADE), 1, 1);
    }

}
