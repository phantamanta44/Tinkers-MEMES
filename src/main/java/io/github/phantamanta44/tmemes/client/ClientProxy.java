package io.github.phantamanta44.tmemes.client;

import io.github.phantamanta44.tmemes.CommonProxy;
import io.github.phantamanta44.tmemes.MEMES;
import io.github.phantamanta44.tmemes.client.book.BookTransformerAppendModifiers;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.Triple;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.TinkerBook;

import java.util.Collection;
import java.util.LinkedList;

public class ClientProxy extends CommonProxy {

    public static final BookRepository TCON_BOOK_REPO = new FileRepository("tconstruct:book");

    private final Collection<Triple<Item, Integer, ModelResourceLocation>> modelsToRegister = new LinkedList<>();

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(new MemeEnergyTooltipHandler());
    }

    @Override
    public void registerModel(Item item, int meta, String name) {
        modelsToRegister.add(Triple.of(item, meta, new ModelResourceLocation(MEMES.MOD_PREF + name, "inventory")));
    }

    @Override
    public void registerBookData() {
        TinkerBook.INSTANCE.addTransformer(new BookTransformerAppendModifiers());
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        modelsToRegister.forEach(r ->
                ModelLoader.setCustomModelResourceLocation(r.getLeft(), r.getMiddle(), r.getRight()));
        modelsToRegister.clear();
    }

}
