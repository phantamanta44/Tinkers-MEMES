package io.github.phantamanta44.tmemes.integration.conarm.client;

import io.github.phantamanta44.tmemes.integration.conarm.MemeArmourTraits;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;

public class BookTransformerArmourModifiers extends SectionTransformer {

    public BookTransformerArmourModifiers() {
        super("modifiers");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        ContentListing listing = (ContentListing)section.pages.get(0).content;
        MemeArmourTraits.ARMOUR_MODS.forEach(trait -> {
            PageData page = new PageData();
            page.source = section.source;
            page.parent = section;
            page.type = "armormodifier";
            page.data = "modifiers/" + trait.identifier + ".json";
            section.pages.add(page);
            page.load();
            listing.addEntry(trait.getLocalizedName(), page);
        });
    }

}
