package io.github.phantamanta44.tmemes.trait;

import io.github.phantamanta44.tmemes.item.MemeItems;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("NullableProblems")
public class MemeTraits {

    public static TraitElectromechanical ELECTRIC;
    public static TraitDisassembling DISASSEMBLE;
    public static TraitDirectedFluxField ELECTRIC_REDIRECT;
    public static final List<ModifierTrait> MEME_MODIFIERS = new ArrayList<>();

    public static void init() {
        MEME_MODIFIERS.add(ELECTRIC = new TraitElectromechanical());
        ELECTRIC.addItem(new ItemStack(MemeItems.ELECTRIC_UPGRADE, 1, 0), 1, 1);
        MEME_MODIFIERS.add(DISASSEMBLE = new TraitDisassembling());
        DISASSEMBLE.addItem("circuitUltimate");
        MEME_MODIFIERS.add(ELECTRIC_REDIRECT = new TraitDirectedFluxField());
        ELECTRIC_REDIRECT.addItem(new ItemStack(MemeItems.ELECTRIC_UPGRADE, 1, 1), 1, 1);
    }

    public static Stream<ModifierTrait> stream() {
        return MEME_MODIFIERS.stream();
    }

}
