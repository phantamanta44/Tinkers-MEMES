package io.github.phantamanta44.tmemes;

import io.github.phantamanta44.tmemes.capability.CapabilityEventHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collection;
import java.util.LinkedList;

public class CommonProxy {

    private final Collection<Item> itemsToRegister = new LinkedList<>();

    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());
    }

    public void registerItem(Item item) {
        itemsToRegister.add(item);
    }

    public void registerModel(Item item, int meta, String name) {
        // NO-OP
    }

    public void registerBookData() {
        // NO-OP
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        itemsToRegister.forEach(event.getRegistry()::register);
    }

}
