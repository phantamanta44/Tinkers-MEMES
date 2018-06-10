package io.github.phantamanta44.tmemes.client;

import io.github.phantamanta44.tmemes.CommonProxy;
import io.github.phantamanta44.tmemes.MEMES;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Collection;
import java.util.LinkedList;

public class ClientProxy extends CommonProxy {

    private final Collection<Triple<Item, Integer, ModelResourceLocation>> modelsToRegister = new LinkedList<>();

    @Override
    public void registerModel(Item item, int meta, String name) {
        modelsToRegister.add(Triple.of(item, meta, new ModelResourceLocation(MEMES.MOD_PREF + name, "inventory")));
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        System.out.println("Model event fired");
        modelsToRegister.forEach(r ->
                ModelLoader.setCustomModelResourceLocation(r.getLeft(), r.getMiddle(), r.getRight()));
        modelsToRegister.clear();
    }

}
