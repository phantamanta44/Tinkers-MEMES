package io.github.phantamanta44.tmemes.trait;

import io.github.phantamanta44.tmemes.item.MemeItems;

@SuppressWarnings("NullableProblems")
public class MemeTraits {

    public static TraitElectromechanical ELECTRIC;
    public static TraitDisassembling DISASSEMBLE;

    public static void init() {
        ELECTRIC = new TraitElectromechanical();
        ELECTRIC.addItem(MemeItems.ELECTRIC_UPGRADE);
        DISASSEMBLE = new TraitDisassembling();
        DISASSEMBLE.addItem("circuitUltimate");
    }

}
