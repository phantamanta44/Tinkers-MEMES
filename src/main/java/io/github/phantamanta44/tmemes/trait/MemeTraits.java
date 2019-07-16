package io.github.phantamanta44.tmemes.trait;

import io.github.phantamanta44.tmemes.MemeConfig;
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
        if (MemeConfig.enableElectromechanical) {
            MEME_MODIFIERS.add(ELECTRIC = new TraitElectromechanical());
            ELECTRIC.addItem(new ItemStack(MemeItems.ELECTRIC_UPGRADE, 1, 0), 1, 1);
        }
        if (MemeConfig.enableDisassembling) {
            MEME_MODIFIERS.add(DISASSEMBLE = new TraitDisassembling());
            DISASSEMBLE.addItem("circuitUltimate");
        }
        if (MemeConfig.enableDirectedFluxField) {
            MEME_MODIFIERS.add(ELECTRIC_REDIRECT = new TraitDirectedFluxField());
            ELECTRIC_REDIRECT.addItem(new ItemStack(MemeItems.ELECTRIC_UPGRADE, 1, 1), 1, 1);
        }
    }

    public static Stream<ModifierTrait> stream() {
        return MEME_MODIFIERS.stream();
    }

}
