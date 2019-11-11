package io.github.phantamanta44.tmemes.integration.conarm.client;

import c4.conarm.lib.book.ArmoryBook;
import io.github.phantamanta44.tmemes.integration.conarm.MemeIntegrationConArm;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.mantle.client.book.repository.FileRepository;

public class MemeIntegrationConArmClient extends MemeIntegrationConArm {

    public static final BookRepository CONARM_BOOK_REPO = new FileRepository("conarm:book");

    @Override
    public void postInit() {
        super.postInit();
        ArmoryBook.INSTANCE.addTransformer(new BookTransformerArmourModifiers());
    }

}
