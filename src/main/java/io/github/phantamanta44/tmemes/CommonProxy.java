package io.github.phantamanta44.tmemes;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collection;
import java.util.LinkedList;

public class CommonProxy {

    private final Collection<Item> itemsToRegister = new LinkedList<>();

    public CommonProxy() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void registerItem(Item item) {
        itemsToRegister.add(item);
    }

    public void registerModel(Item item, int meta, String name) {
        // NO-OP
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        itemsToRegister.forEach(event.getRegistry()::register);
    }

}
