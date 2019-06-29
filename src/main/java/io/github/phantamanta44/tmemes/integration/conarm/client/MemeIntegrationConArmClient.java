package io.github.phantamanta44.tmemes.integration.conarm.client;

import c4.conarm.lib.book.ArmoryBook;
import io.github.phantamanta44.tmemes.integration.conarm.MemeIntegrationConArm;

public class MemeIntegrationConArmClient extends MemeIntegrationConArm {

    @Override
    public void postInit() {
        super.postInit();
        ArmoryBook.INSTANCE.addTransformer(new BookTransformerArmourModifiers());
    }

}
