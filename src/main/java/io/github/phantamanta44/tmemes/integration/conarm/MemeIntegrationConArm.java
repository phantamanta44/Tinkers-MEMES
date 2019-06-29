package io.github.phantamanta44.tmemes.integration.conarm;

import c4.conarm.lib.armor.ArmorCore;
import c4.conarm.lib.book.ArmoryBook;
import io.github.phantamanta44.tmemes.ElectricToolRegistry;
import io.github.phantamanta44.tmemes.integration.MemeIntegration;

public class MemeIntegrationConArm implements MemeIntegration {

    @Override
    public void preInit() {
        ElectricToolRegistry.registerPredicate(s -> s.getItem() instanceof ArmorCore);
    }

    @Override
    public void postInit() {
        MemeArmourTraits.init();
        ArmoryBook.INSTANCE.addTransformer(new BookTransformerArmourModifiers());
    }

}
